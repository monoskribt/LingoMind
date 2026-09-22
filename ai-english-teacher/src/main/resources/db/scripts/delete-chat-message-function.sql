CREATE OR REPLACE FUNCTION delete_user_message()
RETURNS TRIGGER AS $$

BEGIN
    WITH message AS (
        SELECT id
        FROM chat_message
        WHERE user_id = NEW.user_id
        ORDER BY created_at DESC
        OFFSET 20)
    DELETE FROM chat_message
    WHERE id IN (SELECT id FROM message);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;