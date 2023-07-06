INSERT INTO pet (pet_img_path, pet_tag) VALUES ('', 'test') ON CONFLICT DO NOTHING;

INSERT INTO comment (comment_message) VALUES ('test') ON CONFLICT DO NOTHING;
