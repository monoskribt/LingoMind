CREATE TRIGGER trg_delete_user_message()
AFTER INSERT ON chat_message
FOR EACH ROW
EXECUTE FUNCTION delete_user_message();