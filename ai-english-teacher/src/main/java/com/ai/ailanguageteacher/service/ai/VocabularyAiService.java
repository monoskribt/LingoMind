package com.ai.ailanguageteacher.service.ai;

import com.ai.ailanguageteacher.dto.request.QuizletFilterRequest;
import com.ai.ailanguageteacher.dto.request.SentencesFilterRequest;
import com.ai.ailanguageteacher.dto.response.QuizletResponse;
import com.ai.ailanguageteacher.dto.response.SentencesResponse;
import com.ai.ailanguageteacher.dto.session.QuizSession;
import com.ai.ailanguageteacher.dto.session.SentenceGapSession;
import com.ai.ailanguageteacher.mapper.ContentMapper;
import com.ai.ailanguageteacher.service.VocabularyCacheManager;
import com.ai.ailanguageteacher.util.CacheUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class VocabularyAiService {

    private final ChatClient chatClient;
    private final VocabularyCacheManager cacheManager;
    private final ContentMapper mapper;

    @Value("classpath:/prompt-templates/quizlet-system.st")
    Resource quizletPrompt;

    @Value("classpath:/prompt-templates/sentences-with-gap-system.st")
    Resource sentencesWithGapPrompt;

    public VocabularyAiService(@Qualifier("chatClientVocabulary") ChatClient chatClient,
                               VocabularyCacheManager cacheManager, ContentMapper mapper) {
        this.chatClient = chatClient;
        this.cacheManager = cacheManager;
        this.mapper = mapper;
    }

    public QuizletResponse generateQuizlet(QuizletFilterRequest request, Long userId) {
        var response = generateAIResponse(request, QuizletResponse.class, userId, quizletPrompt);

        var session = new QuizSession();
        session.setCards(response.getCards());
        session.setCurrentIndex(0);
        session.setCorrect(0);
        session.setIncorrect(0);
        session.setLevel(request.getEnglishLevel());

        cacheManager.save(CacheUtils.QUIZ_CACHE, CacheUtils.QUIZ_CACHE_KEY.formatted(userId), session);

        return response;
    }


    public SentencesResponse generateSentencesWithGap(SentencesFilterRequest request, Long userId) {
        var response =  generateAIResponse(request, SentencesResponse.class, userId, sentencesWithGapPrompt);

        var session = new SentenceGapSession();
        session.setCards(response.getCards());
        session.setCurrentIndex(0);
        session.setCorrect(0);
        session.setIncorrect(0);
        session.setLevel(request.getEnglishLevel());

        cacheManager.save(CacheUtils.SENTENCE_GAP_CACHE, CacheUtils.SENTENCE_GAP_CACHE_KEY.formatted(userId), session);

        return response;
    }

    private <Request, Response> Response generateAIResponse(Request request, Class<Response> response, Long userId,
                                                            Resource prompt) {
        return chatClient.prompt()
                .user(mapper.toJson(request))
                .system(prompt)
                .advisors(a -> a.param(CONVERSATION_ID, userId))
                .call()
                .responseEntity(response)
                .entity();
    }
}
