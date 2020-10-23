INSERT INTO human (id, created_at, dna, is_mutating, name) VALUES (1, '2020-10-20', 'ATGCGA;CAGTGC;TTATGT;AGAAGG;CCCCTA;TCACTG', 1, 'Humano 1')
INSERT INTO human (id, created_at, dna, is_mutating, name) VALUES (2, '2020-10-20', 'ATGCGA;CAGTGC;TTATTT;AGACGG;GCGTCA;TCACTG', 0, 'Humano 2')
INSERT INTO human (id, created_at, dna, is_mutating, name) VALUES (3, '2020-03-19', 'CGACGA;CAGTGC;TTATTT;AGACGG;GCGTCA;TCACTG', 0, 'Humano 3')

INSERT INTO users (username, password, name, enabled) VALUES ('user', '$2a$10$FoVvhPvxTCpPj2GoSmg79eCBDlDFRHlfrI9HWi6O6lw.dG/z2QpEa', 'Roberto', 1);
INSERT INTO users (username, password, name, enabled) VALUES ('admin', '$2a$10$FoVvhPvxTCpPj2GoSmg79eCBDlDFRHlfrI9HWi6O6lw.dG/z2QpEa', 'John', 1);

INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');

INSERT INTO users_role (user_id, role_id) VALUES (1, 1);
INSERT INTO users_role (user_id, role_id) VALUES (2, 1);
INSERT INTO users_role (user_id, role_id) VALUES (2, 2);