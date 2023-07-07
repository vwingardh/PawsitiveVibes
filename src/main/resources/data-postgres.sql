INSERT INTO pet (pet_id, pet_img, pet_tag) VALUES (1, '/pet-uploads/catness.jpeg', 'cat') ON CONFLICT DO NOTHING;
-- TRUNCATE TABLE pet CASCADE;
INSERT INTO comment (comment_message, pet_id) VALUES ('Very cute', 1) ON CONFLICT DO NOTHING;
