create table IF NOT EXISTS clinic_specialty(
id_specialty serial primary key,
name_specialty varchar(255) unique not null,
created_at timestamp default current_timestamp
);


insert into clinic_specialty (name_specialty) values (
'Dermatology'
),
('Ophthalmology'),
('Radiology'),
('Family Medicine'),
('Pediatrics');


create table IF NOT EXISTS  patient(
id_patient serial primary key,
patient_name varchar(255) not null,
age int not null,
created_at timestamp default current_timestamp
);


insert into patient (patient_name, age)
values
('Manuel', 53),
('Joana', 32),
('Ana', 25),
('Diogo', 33),
('Catarina', 33),
('Miguel', 40);


create table doctor(
id_doctor serial primary key,
doctor_name varchar(255) not null,
id_specialty int not null,
created_at timestamp default current_timestamp,
foreign key (id_specialty) references clinic_specialty(id_specialty) on delete restrict
);


CREATE OR REPLACE FUNCTION get_or_create_specialty(specialty_name VARCHAR)
RETURNS INTEGER AS $$
DECLARE
    specialty_id INTEGER;
BEGIN
    SELECT id_specialty INTO specialty_id
    FROM clinic_specialty
    WHERE name_specialty ILIKE specialty_name;

    IF specialty_id IS NULL THEN
        INSERT INTO clinic_specialty (name_specialty)
        VALUES (specialty_name)
        RETURNING id_specialty INTO specialty_id;
    END IF;

    RETURN specialty_id;
END;
$$ LANGUAGE plpgsql;


insert into doctor (doctor_name, id_specialty)
values
('António', get_or_create_specialty('Dermatology')),
('Maria', get_or_create_specialty('Ophthalmology')),
('Carlos', get_or_create_specialty('Radiology')),
('Gabriela', get_or_create_specialty('Family Medicine')),
('Paulo', get_or_create_specialty('Pediatrics'));

create table IF NOT EXISTS  clinic_pathology(
id_pathology serial primary key,
name_pathology varchar(255) unique not null,
created_at timestamp default current_timestamp
);



INSERT INTO clinic_pathology (name_pathology)
SELECT 'Pathology ' || gs::TEXT
FROM generate_series(1, 7) AS gs;



create table IF NOT EXISTS  clinic_symptom(
id_symptom serial primary key,
name_symptom varchar(255) unique not null,
created_at timestamp default current_timestamp
);

INSERT INTO clinic_symptom (name_symptom)
SELECT 'Symptom ' || gs::text || ' Description'
FROM generate_series(1, 15) AS gs;

create table IF NOT EXISTS  pathology_symptom (
    id_pathology INT NOT NULL,
    id_symptom INT NOT NULL,
    PRIMARY KEY (id_pathology, id_symptom),
    FOREIGN KEY (id_pathology) REFERENCES clinic_pathology(id_pathology) ON DELETE CASCADE,
    FOREIGN KEY (id_symptom) REFERENCES clinic_symptom(id_symptom) ON DELETE CASCADE
);


create or replace function find_pathology_id(pathology_name varchar)
RETURNS INTEGER AS $$
DECLARE
    pathology_id INTEGER;
BEGIN
    SELECT id_pathology INTO pathology_id
    FROM clinic_pathology
    WHERE name_pathology ILIKE pathology_name;
    RETURN pathology_id;
END;
$$ LANGUAGE plpgsql;

create or replace function find_symptom_id(symptom_name varchar)
RETURNS INTEGER AS $$
DECLARE
    symptom_id INTEGER;
BEGIN
    SELECT id_symptom INTO symptom_id
    FROM clinic_symptom
    WHERE name_symptom ILIKE symptom_name;
    RETURN symptom_id;
END;
$$ LANGUAGE plpgsql;

insert into pathology_symptom(id_pathology, id_symptom)
values
(find_pathology_id('Pathology 1'), find_symptom_id('Symptom 1 Description')),
(find_pathology_id('Pathology 1'), find_symptom_id('Symptom 2 Description')),

(find_pathology_id('Pathology 2'), find_symptom_id('Symptom 3 Description')),
(find_pathology_id('Pathology 2'), find_symptom_id('Symptom 4 Description')),
(find_pathology_id('Pathology 2'), find_symptom_id('Symptom 5 Description')),

(find_pathology_id('Pathology 3'), find_symptom_id('Symptom 6 Description')),
(find_pathology_id('Pathology 3'), find_symptom_id('Symptom 7 Description')),

(find_pathology_id('Pathology 4'), find_symptom_id('Symptom 8 Description')),

(find_pathology_id('Pathology 5'), find_symptom_id('Symptom 9 Description')),
(find_pathology_id('Pathology 5'), find_symptom_id('Symptom 10 Description')),
(find_pathology_id('Pathology 5'), find_symptom_id('Symptom 11 Description')),

(find_pathology_id('Pathology 6'), find_symptom_id('Symptom 12 Description')),
(find_pathology_id('Pathology 6'), find_symptom_id('Symptom 13 Description')),

(find_pathology_id('Pathology 7'), find_symptom_id('Symptom 14 Description')),
(find_pathology_id('Pathology 7'), find_symptom_id('Symptom 15 Description'))
;


create table IF NOT EXISTS  consult(
id_consult serial primary key,
id_doctor int not null,
id_patient int not null,
id_pathology int not null,
FOREIGN KEY (id_doctor) REFERENCES doctor(id_doctor),
FOREIGN KEY (id_patient) REFERENCES patient(id_patient),
FOREIGN KEY (id_pathology) REFERENCES clinic_pathology(id_pathology),
created_at timestamp default current_timestamp
);


create or replace function find_doctor_id(name_doctor varchar)
RETURNS INTEGER AS $$
DECLARE
    doctor_id INTEGER;
BEGIN
    SELECT id_doctor INTO doctor_id
    FROM doctor
    WHERE doctor_name ILIKE name_doctor;
    RETURN doctor_id;
END;
$$ LANGUAGE plpgsql;

create or replace function find_patient_id(name_patiente varchar)
returns INTEGER as $$
declare
	patient_id integer;
begin
	select id_patient into patient_id
	from patient
	where patient_name ilike name_patiente;
	return patient_id;
end;
$$ LANGUAGE plpgsql;



insert into consult(id_doctor, id_patient, id_pathology)
values
(find_doctor_id('António'), find_patient_id('Manuel'), find_pathology_id('Pathology 1')),
(find_doctor_id('António'), find_patient_id('Manuel'), find_pathology_id('Pathology 2')),
(find_doctor_id('Maria'), find_patient_id('Manuel'), find_pathology_id('Pathology 3')),
(find_doctor_id('Maria'), find_patient_id('Joana'), find_pathology_id('Pathology 3')),
(find_doctor_id('Carlos'), find_patient_id('Ana'), find_pathology_id('Pathology 4')),
(find_doctor_id('Gabriela'), find_patient_id('Diogo'), find_pathology_id('Pathology 5')),
(find_doctor_id('Paulo'), find_patient_id('Catarina'), find_pathology_id('Pathology 6')),
(find_doctor_id('Maria'), find_patient_id('Miguel'), find_pathology_id('Pathology 7'))
;
















