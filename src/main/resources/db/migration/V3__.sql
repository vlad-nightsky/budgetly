-- Удаляем старое поле
ALTER TABLE budgyscheme.tbank_transaction DROP COLUMN row_hash;
-- Создаём новое
ALTER TABLE budgyscheme.tbank_transaction ADD COLUMN row_hash INTEGER;