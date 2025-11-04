
INSERT INTO doctors (id, name, specialty, active)
VALUES
('33333333-3333-3333-3333-333333333333', 'Dr. House', 'CARDIOLOGY', true),
('44444444-4444-4444-4444-444444444444', 'Dr. Strange', 'NEUROLOGY', false),
('55555555-5555-5555-5555-555555555555', 'Dr. Meredith Grey', 'GENERAL_PRACTICE', true),
('66666666-6666-6666-6666-666666666666', 'Dr. Lisa Cuddy', 'GYNECOLOGY', true),
('77777777-7777-7777-7777-777777777777', 'Dr. Gregory Pratt', 'CARDIOLOGY', false),
('88888888-8888-8888-8888-888888888888', 'Dr. Cristina Yang', 'CARDIOLOGY', true),
('99999999-9999-9999-9999-999999999999', 'Dr. Miranda Bailey', 'PEDIATRICS', true),
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Dr. Derek Shepherd', 'NEUROLOGY', true),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Dr. Arizona Robbins', 'ORTHOPEDICS', false),
('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Dr. Owen Hunt', 'ORTHOPEDICS', true);

-- Tornar Dr. Derek Shepherd inativo
UPDATE doctors SET active = false WHERE id = 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa';