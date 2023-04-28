INSERT INTO p_patient (id, p_active, p_deceasedboolean, p_gender, p_birthdate) VALUES ('00000000-0000-0000-0000-000000000000', true, false, 'male', '1999-01-01');
INSERT INTO p_patient (id, p_active, p_deceasedboolean, p_gender, p_birthdate) VALUES ('00000000-0000-0000-0000-000000000001', true, false, 'female', '1980-02-02');

INSERT INTO hn_humanname (id, hn_family, hn_use, hn_pe_id, hn_p_id) VALUES ('00000000-0000-0000-0000-000000000022', 'Payer', 'official', NULL, '00000000-0000-0000-0000-000000000000');
INSERT INTO hn_humanname (id, hn_family, hn_use, hn_pe_id, hn_p_id) VALUES ('00000000-0000-0000-0000-000000000023', 'Dadaeva', 'official', NULL, '00000000-0000-0000-0000-000000000001');


-- 1:1 Beziehungen mit dem Patienten müssen vor dem Patienten eingefügt werden.
INSERT INTO p_patient (id, p_active, p_deceasedboolean, p_gender, p_birthdate) VALUES ('00000000-0000-0000-0000-000000000002', false, true, 'other', '2004-03-26');
-- 1:n Beziehungen mit dem Patienten müssen nach dem Patienten eingefügt werden
INSERT INTO i_identifier (id, i_system, i_use, i_value, i_p_id) VALUES ('00000000-0000-0000-0000-000000000003', 'urn:oid:1.2.36.146.595.217.0.1', 'usual', '12345', '00000000-0000-0000-0000-000000000002');
INSERT INTO hn_humanname (id, hn_family, hn_use, hn_pe_id, hn_p_id) VALUES ('00000000-0000-0000-0000-000000000004', 'Pirker', 'official', NULL, '00000000-0000-0000-0000-000000000002');
INSERT INTO hn_humanname (id, hn_family, hn_use, hn_pe_id, hn_p_id) VALUES ('00000000-0000-0000-0000-000000000005', 'Pirker', 'usual', NULL, '00000000-0000-0000-0000-000000000002');
INSERT INTO given_humanname (id, given) VALUES ('00000000-0000-0000-0000-000000000005','Simon');
INSERT INTO given_humanname (id, given) VALUES ('00000000-0000-0000-0000-000000000005','Kein weiterer');
INSERT INTO prefix_humanname (id, prefix) VALUES ('00000000-0000-0000-0000-000000000005','Ing.');
INSERT INTO prefix_humanname (id, prefix) VALUES ('00000000-0000-0000-0000-000000000005','Dipl.Ing.');
INSERT INTO prefix_humanname (id, prefix) VALUES ('00000000-0000-0000-0000-000000000005','Mag.');
INSERT INTO prefix_humanname (id, prefix) VALUES ('00000000-0000-0000-0000-000000000005','Dr.phil.');
INSERT INTO prefix_humanname (id, prefix) VALUES ('00000000-0000-0000-0000-000000000005','Dr.');
INSERT INTO suffix_humanname (id, suffix) VALUES ('00000000-0000-0000-0000-000000000005','Bakk');
INSERT INTO suffix_humanname (id, suffix) VALUES ('00000000-0000-0000-0000-000000000005','MSc');
-- Insert für telecom
INSERT INTO pe_period (id, pe_end, pe_start) VALUES ('00000000-0000-0000-0000-000000000006', '2040-01-05', '1999-01-01');
INSERT INTO cp_contactpoint (id, cp_rank, cp_system, cp_use, cp_value, cp_p_id, cp_pe_id) VALUES ('00000000-0000-0000-0000-000000000007', '1', 'phone', 'work', '015552231123', '00000000-0000-0000-0000-000000000002','00000000-0000-0000-0000-000000000006');
INSERT INTO cp_contactpoint (id, cp_rank, cp_system, cp_use, cp_value, cp_p_id) VALUES ('00000000-0000-0000-0000-000000000008', '2', 'email', 'work', 'pirker@spengergasse.at', '00000000-0000-0000-0000-000000000002');
--Insert für address
INSERT INTO pe_period (id, pe_end, pe_start) VALUES ('00000000-0000-0000-0000-000000000009', '2040-01-05', '1999-01-01');
INSERT INTO a_address (id, a_city, a_country, a_district,  a_postalcode, a_state, a_type, a_use, a_p_id, a_pe_id) VALUES ('00000000-0000-0000-0000-000000000010', 'Wien', 'Österreich', 'Wien', '1050', 'Wien', 'both', 'home', '00000000-0000-0000-0000-000000000002','00000000-0000-0000-0000-000000000009');
INSERT INTO a_address_line (address_id, line) VALUES ('00000000-0000-0000-0000-000000000010','Simon Pirker');
INSERT INTO a_address_line (address_id, line) VALUES ('00000000-0000-0000-0000-000000000010','Spengergasse 20');
INSERT INTO a_address_line (address_id, line) VALUES ('00000000-0000-0000-0000-000000000010','1050 Wien');

--Inserts für practitioner
INSERT INTO `pra_practitioner` (`id`, `pra_active`, `pra_birthdate`, `pra_gender`) VALUES ('00000000-0000-0000-0001-000000000000', true, '2000-02-22', 'female');
INSERT INTO `pra_practitioner` (`id`, `pra_active`, `pra_birthdate`, `pra_gender`) VALUES ('00000000-0000-0000-0002-000000000000', false, '1990-09-19', 'male');
INSERT INTO `pra_practitioner` (`id`, `pra_active`, `pra_birthdate`, `pra_gender`) VALUES ('00000000-0000-0000-0003-000000000000', true, '1999-10-12', 'other');
--Insert für Identifier
INSERT INTO `i_identifier` (`id`, `i_system`, `i_use`, `i_value`, `i_pra_id`) VALUES ('00000000-0000-0000-0022-000000000000','urn:oid:1.2.34.146.595.217.0.1', 'temp', '12345', '00000000-0000-0000-0001-000000000000');
--Insert für HumanName
INSERT INTO `hn_humanname` (`id`, `hn_family`, `hn_use`, `hn_pra_id`) VALUES ('00000000-0000-0000-0000-000000000222', 'Payer', 'official', '00000000-0000-0000-0002-000000000000');
INSERT INTO `given_humanname` (`id`, `given`) VALUES ('00000000-0000-0000-0000-000000000222','Sophie');
--Insert für telecom
INSERT INTO `pe_period` (`id`, `pe_end`, `pe_start`) VALUES ('22000000-0000-0000-0000-000000000000', '2024-02-02 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO `cp_contactpoint` (`id`, `cp_rank`, `cp_system`, `cp_use`, `cp_value`, `cp_pe_id`, `cp_pra_id`) VALUES ('00000022-0000-0000-0000-000000000000', '2', 'phone', 'work', '069911223344', '22000000-0000-0000-0000-000000000000', '00000000-0000-0000-0002-000000000000');
--Insert für address
INSERT INTO `pe_period` (`id`, `pe_end`, `pe_start`) VALUES ('11000000-0000-0000-0000-000000000000', '2030-12-12 ', '2000-12-12');
INSERT INTO `a_address` (`id`, `a_city`, `a_country`, `a_district`, `a_postalcode`, `a_state`, `a_text`, `a_type`, `a_use`, `a_pe_id`, `a_pra_id`) VALUES ('11100000-0000-0000-0000-000000000000', 'Wien', 'Österreich', 'Wien', '1050', 'Wien', 'Spengergasse 20, 1050, Wien', 'physical', 'home', '11000000-0000-0000-0000-000000000000', '00000000-0000-0000-0002-000000000000');
--Insert für attachment
INSERT INTO `at_attachment` (`id`, `at_contenttype`, `at_creation`, `at_data`, `at_language`, `at_title`, `at_pra_id`) VALUES ('00000012-0000-0000-0000-000000000000', 'application/pdf', '2022-02-22', '/9j/4...KAP//Z', 'en', 'Definition of Procedure', '00000000-0000-0000-0002-000000000000');
--Insert für qualification
INSERT INTO `cc_codeableconcept` (`id`, `cc_text`, `cc_pra_id`) VALUES ('00000112-0000-0000-0000-000000000000', 'general headache', '00000000-0000-0000-0002-000000000000');
INSERT INTO `co_coding` (`id`, `co_code`, `co_system`, `co_user_selected`, `co_cc_id`) VALUES ('00066000-0000-0000-0000-000000000000', '25064002', 'http://snomed.info/sct', true, '00000112-0000-0000-0000-000000000000');
INSERT INTO `pe_period` (`id`, `pe_end`, `pe_start`) VALUES ('33000000-0000-0000-0000-000000000000', '2040-02-02', '2010-02-02');
INSERT INTO `re_reference` (`id`, `re_display`, `re_reference`) VALUES ('00000000-0011-0000-0000-000000000000', 'HL7, Inc.', 'Organization/123');
INSERT INTO `qu_qualification` (`id`, `qu_cc_id`, `qu_pe_id`, `qu_re_id`, `be_pra_id`) VALUES ('00000000-0000-0000-0133-000000000000', '00000112-0000-0000-0000-000000000000', '33000000-0000-0000-0000-000000000000', '00000000-0011-0000-0000-000000000000', '00000000-0000-0000-0002-000000000000');

--Insert von Uebung
--Insert für Pracitioner
INSERT INTO `n_narrative` (`id`, `n_div`, `n_status`) VALUES ('00000000-0000-0000-0000-000000001111', '<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative</b></p><div style=\"display: inline-block; background-color: --d9e0e7; padding: 6px; margin: 4px; border: 1px solid --8da1b4; border-radius: 5px; line-height: 60%\"><p style=\"margin-bottom: 0px\">Resource &quot;00000000-0000-0000-0004-000000000000&quot; </p></div><p><b>identifier</b>: id: 118265112 (OFFICIAL), id: 191REW8WE916 (USUAL)</p><p><b>name</b>: Langeveld Anne (OFFICIAL)</p><p><b>telecom</b>: ph: 0205563847(WORK), <a href=\"mailto:a.langeveld@bmc.nl\">a.langeveld@bmc.nl</a>, fax: 0205668916(WORK)</p><p><b>address</b>: Galapagosweg 9 Amsterdam 1105 AZ NLD (WORK)</p><p><b>gender</b>: female</p><p><b>birthDate</b>: 1959-03-11</p><p><b>communication</b>: France <span style=\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\"> (<a href=\"http://terminology.hl7.org/3.1.0/CodeSystem-v3-ietf3066.html\">Tags for the Identification of Languages</a>--fr)</span></p></div>', 'generated');
INSERT INTO `pra_practitioner` (`id`, `pra_active`, `pra_birthdate`, `pra_gender`, `dr_n_id`) VALUES ('00000000-0000-0000-0004-000000000000', true, '1959-03-11', 'female', '00000000-0000-0000-0000-000000001111');

--Insert für Identifier
INSERT INTO `i_identifier` (`id`, `i_use`, `i_system`, `i_value`, `i_pra_id`) VALUES ('00000000-0000-0000-0000-550000000000','official', 'urn:oid:2.16.528.1.1007.3.1', '118265112', '00000000-0000-0000-0004-000000000000');
INSERT INTO `i_identifier` (`id`, `i_use`, `i_system`, `i_value`, `i_pra_id`) VALUES ('00000000-0000-0000-0000-560000000000','usual', 'urn:oid:2.16.840.1.113883.2.4.6.3', '191REW8WE916', '00000000-0000-0000-0004-000000000000');
--Insert für humanname
INSERT INTO `hn_humanname` (`id`, `hn_use`, `hn_family`, `hn_pra_id`) VALUES ('00000000-0000-0000-0000-000000324000', 'official', 'Anne', '00000000-0000-0000-0004-000000000000');
INSERT INTO `given_humanname` (`id`, `given`) VALUES ('00000000-0000-0000-0000-000000324000', 'Langeveld');
INSERT INTO `suffix_humanname` (`id`, `suffix`) VALUES ('00000000-0000-0000-0000-000000324000', 'MD');
--Insert für telecom
INSERT INTO `cp_contactpoint` (`id`, `cp_system`, `cp_value`, `cp_use`, `cp_pra_id`) VALUES ('00230000-0000-0000-0000-000000000000', 'phone', '0205563847', 'work', '00000000-0000-0000-0004-000000000000');
INSERT INTO `cp_contactpoint` (`id`, `cp_system`, `cp_value`, `cp_use`, `cp_pra_id`) VALUES ('00240000-0000-0000-0000-000000000000', 'email', 'a.langeveld@bmc.nl', 'work', '00000000-0000-0000-0004-000000000000');
INSERT INTO `cp_contactpoint` (`id`, `cp_system`, `cp_value`, `cp_use`, `cp_pra_id`) VALUES ('00250000-0000-0000-0000-000000000000', 'fax', '0205668916', 'work', '00000000-0000-0000-0004-000000000000');
--Insert für address
INSERT INTO `a_address` (`id`, `a_city`, `a_postalcode`, `a_country`, `a_pra_id`) VALUES ('00034000-0000-0000-0000-000000000000', 'Amsterdam', '1105 AZ', 'NLD', '00000000-0000-0000-0004-000000000000');
INSERT INTO `a_address_line` (`address_id`, `line`) VALUES ('00034000-0000-0000-0000-000000000000','Galapagosweg 9');
--Insert für attachment
INSERT INTO `at_attachment` (`id`, `at_contenttype`, `at_data`, `at_pra_id`) VALUES ('02000000-0000-0000-0000-000000000000', 'image/jpeg', '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', '00000000-0000-0000-0004-000000000000');
--Insert für codeableconcept
INSERT INTO `cc_codeableconcept` (`id`, `cc_pra_id`) VALUES ('00000000-0000-0000-0000-000000057000', '00000000-0000-0000-0004-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_display`, `co_cc_id`) VALUES ('co123', 'urn:ietf:bcp:47', 'fr', 'France', '00000000-0000-0000-0000-000000057000');
--Insert für qualification
INSERT INTO `cc_codeableconcept` (`id`, `cc_text`, `cc_pra_id`) VALUES ('00000000-0000-0000-0000-098000000000','MD','00000000-0000-0000-0004-000000000000');
INSERT INTO `cc_codeableconcept` (`id`, `cc_text`, `cc_pra_id`) VALUES ('00000000-0000-0000-0000-097000000000', 'PhD', '00000000-0000-0000-0004-000000000000');
INSERT INTO `co_coding` (`id`, `co_code`, `co_system`, `co_display`, `co_user_selected`, `co_cc_id`) VALUES ('00000900-0000-0000-0000-000000000000', '677091', 'https://fhir.cerner.com/ec2458f2-1e24-41c8-b71b-0e701af7583d/codeSet/29600', 'MD', true, '00000000-0000-0000-0000-098000000000');
INSERT INTO `co_coding` (`id`, `co_code`, `co_system`, `co_display`, `co_user_selected`, `co_cc_id`) VALUES ('00000800-0000-0000-0000-000000000000', '677079', 'https://fhir.cerner.com/ec2458f2-1e24-41c8-b71b-0e701af7583d/codeSet/29600', 'PhD', true, '00000000-0000-0000-0000-097000000000');
INSERT INTO `pe_period` (`id`,`pe_start`) VALUES ('00770000-0000-0000-0000-000000000000', '2014-07-09 06:00:00.000');
INSERT INTO `pe_period` (`id`,`pe_start`) VALUES ('00760000-0000-0000-0000-000000000000', '2014-07-09 06:00:00.000');
INSERT INTO `qu_qualification` (`id`, `qu_cc_id`, `qu_pe_id`, `be_pra_id`) VALUES ('99900000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-098000000000', '00770000-0000-0000-0000-000000000000', '00000000-0000-0000-0004-000000000000');
INSERT INTO `qu_qualification` (`id`, `qu_cc_id`, `qu_pe_id`, `be_pra_id`) VALUES ('99800000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-097000000000', '00760000-0000-0000-0000-000000000000', '00000000-0000-0000-0004-000000000000');

--Insert für Reference für Patient
INSERT INTO `i_identifier` (`id`, `i_use`, `i_system`, `i_value`) VALUES ('00000000-0000-0000-0000-000000000030','official', 'urn:oid:2.16.528.1.1007.3.1', '1234567890');
INSERT INTO `re_reference` (`id`, `re_reference`, `re_type`, `re_i_id`, `re_display`, `re_p_id`) VALUES ('00000000-0000-0000-1111-000000000000', 'Practitioner/00000000-0000-0000-0004-000000000000', 'Practitioner', '00000000-0000-0000-0000-000000000030', 'Practitioner', '00000000-0000-0000-0000-000000000002');
--Insert für photo für Patient
INSERT INTO `at_attachment` (`id`, `at_contenttype`, `at_data`, `at_p_id`) VALUES ('00000000-0000-0000-1112-000000000000', 'image/jpeg', 'iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABHNCSVQICAgIfAhkiAAAEYFJREFUeF7NWwl0XOV1/t6MRjPa98WWF9ngBYPtsJhCgk+dQ40TmoJLAENicOtyCgk+pwdyUgwxnATMlnPApuUkkARjt1CwWzAEDE1LsZtiG2J5kVfJsmVrG0mjWTUzmn1ev/ukJ4+lGWkkS0i//c6M5r33z3+/e+93l/+NgmGGqqpFvORWHn/JYwGPSh4VchvPaXcritL/Xj7TDzlnMBi083KM14jEYnjv3Gm8Dl8nMkwdqoKTXNnHccX46RczF7mG+t6Uq+oT/Cne/GMemckmSQZAIjA6OOMpvC8cwgfNZ/FG3A+YL14mdRGGgldjimFjKiAGAUChsrjwdTwe5yHaT2voYAy8eDyFd4eC2Gk9j3+L+hDNNKVcJ61BrGBj10zTqyeVK8OJFyYD4Ge84Bmx7FQzirADBRsIwHgKLutyBgPYZbdie9ADX0avmw01CILKSx7fM/MbL6YEgEKs58nnh1N5MgCGu2csz9uDPdjZZcWuiA9uQy8HpT1UgjDrGy/o1/ffSaGW88P/5MEphx4TCYCY/Y6OZnwc60H3SIWnWLSEOEX83v9WL/pUpNQAoED5fDnDo2w44SfyvLD9lnP12KmEEDSOPrLQg23B/Jw5X5XM6dYBEJN4bCKFS+e73zx5BNvMMSgZGelcPowZ40W6wnqF2hf6dPDIu/RZx2cGD83+7cY6bM+MwTAWwmtWD29XtalUAFjJv3de6tKFF4I2GyLd3cidNWvMFtoV6MEOhrr3EUB8jITvl1VVvisAvMsPVl0qAAEK37B1KzwnT2Lugw+i4sYbL3VK2Cj8dlsLPiXh9RhG7/MpF6Kq2wSAOl4w71JWK9q3HzyI2qefhqe+Hpfddx8W/0zSiRGGKH0RnE80/769DR9H/fCOh/Aa+6NeAOjgWy23H80Q4ePxODr27cOJF16A7+xZTLvjDlz9i18ggyY7ohit+aYKp5h923n8N9neTbaXDIYTjWZ5wxFhpwAQ5FXm0c6uFz7te/fi+PPPw9/YiKqVK3Htxo0wGo0jBsAfCuG9M6ewOyMGOzO8ILUfl2KL8veWXmM4VIQEgBHNq1LbrZ98gpDLhanLlyO7UopDaBZw9Nln+y1gCd+L9qOBABxHjsDb0NB7/ZQpQ7ikimMNp3HGkoHWWBgeihzi1WHmLs5YFOcR04hQwBirkTYA/ZresweHHn8cEY8HtHGUXXcdqm69FdmXX4663/4WrsOHsWDDBpTNnImW3/8e1s8/R6CtTVvvtNtvx7XPPacBI2XyBZdXEWOSY7VaUVVVpVmOFlUotJtpbzQeQ545C/U9PvzaZUWrgdfLzWPgFmkDIH4eDAbRRE2f3rwZkdZWKPxblKGYTJj+wAMwLlkCv9eLqnnzULd2LQIUSDMw0VpeHsoI1MJ165Cfn6/xgww5H6LZ+/1+FBYWasLLiBKQ3e4W1AdYyPFLrsgsxLKSmTju78YmezOaFGa0XzcAPT09aKdQjV98AXdtLVS+N9EVssxmVK9ejdJly6BSs6LdE088ATfNPpybi3hZGSyzZ2Pq0qWYfdVVmqACgKZlgihHHgHSOUM+D0ejeM9xBqVGM27IrYRPjaLSkou2UACbbE04pLLU/zoBkEVFIhF4qeGuri7tCNEN8rj4YosFRTT5ArpBBt8HnE50id8zKXLwvhiFKyguRnl5OUpKSmAmYAKSCB4gR4jwiRFDvitGi6v12lDj70SVOQ83F0yHJcOkAbCZANTEQ18/AHrIC4fDmtlqvEBA2j/8EM4DB1B9110ovf56HH7qKcSpwRmrVqHwhhs0/xYBRXB5FeHlfgGzoKAAJrpQojZ1vgkL8QW78YG7Ed/JnY7FhZUTB4Dur4nsK0I279yJYy++iAh9ePqdd6KCblDz8MNQKGTRokW4lqExj6lx4hBLEguqqKgYFCrF9LtCfrio4daAFzfkVOA3zlP4ZlY5vlUyY2IBGBh6vOfP4//uv1+rAZScHJQwBc655hpYn3kGEWaECglt1r334spHHoGJ52WINbS0tGAmXSaZDzd3O7G1uwEPFc7HLk8TjgecqMzIwoMVC5FrtsAaDk6MCySGLHkvUaHmpz9F265dbJlmwnLLLSi+7TZYaNIuCuii5kEusFDLC9evR9WKFYhSu3a7HVOYC6QisFqnFbuDnVhXvlBzsTgBE2sSghTXmTAOSARABOkmwdU8+ih8R4/COH8+Su6+G1WLFyM7Oxs2WkTrZ58hsH27VhVe9qMfYRpDoGi/jBEh1RCBP7M1wqaGcE/ZvEEgCWiTAgDxYYfDgbP798N97Biy585FFU1fNJtJaxBwmlgTdH35JYy0lBImS+XkgUpmjXI+EUw9EdUt4u32E6gwZePmkuqkVjIpABBNSuLipIlLbiBaLyoqQg79XExVooTb7YaLOYKEOjkvGZ686hmgCO6KBHHM1wVvPIIZmbm4IqcEr1qP4Nu5VRrj9yZ7F/JeuWfCAdD9XzhAXEFeRSgJb3oWp+cMPp9PA0DA0WO/LpBcc8JvxyF3O6abcrA3ZMdDJQvwR28bbsqbitKs3EE7SpMGgHTqJ0lwPEyUxOd1YBI1KnPs77bC2tONm7Iq8IL7OB4rXohsMn0OEx6DMnhLbdIAkJLF+nJ7cQERXjK/VEMs539czXjXfQbZqgFLLGW4q3wuzBRet5Jkmy+TwgWSCaVnb2LykuFJkpM4tJAmB0tc6lZLdT93NmGfrwMVBjNWls3BlKy8ITdSJ60F6MKL2QspJgt1ovGjzO1DkTA5w4hKcw4O8+8cYwaa2fYqUTLxndLLYBqiiTIuAHBh7DhdWodBtBmg4JLfJ5a0iRYghLmx/SC+ZSrBiYgbFRnZmGsuRHFmFreeFRzw27CiqBqWAXXBQCuyshj6JxZDX0kx1EsqQ3nksOcUhjRWsMPuhqWcSIQ/57QjzNzg8tJyZFKAZCPI3v6GjgNYnTsbX/XYUJ6Zg78qmd3v79JpMvRpP1lhFNVcKA4bLejXtmbsj7MXMQa9QoWaURNZeljIEi6QBTW4HPh3axOuK63AX5RXISMFmDY2Ml7uOopllnJ8GbBrRc4tpRcXSQOjhJN7/7U+NxqCfnTEIgjx+yK86BzbZU7R2UQDYPV246W2M2iMR/HD0qm4nYcpBQCnurvwB08z/rZiAY64OlAb9+Bh5vvJrK+bWt7tsuEznxNWzh2MRsAUCrnkiwAtxa2wN2Fi54jfpXWML2EoDFmq1OMjHR5mgv/YfAqNmVQFM8O1hVNwZxIAdILc62rVSHA5GxsfdTdhpiUff106p/8RGv372wN+DdRjsRDymQusyCnGzUXlKGN+IEqXDq6HgBz0uvCJ184d3RhiWtd4dECMGABh8xZqfkNLHVpzLdommxqN4QGmrqtKLrYArd3c57vn/G4c8tkQZoOzmmnvNXmVgxohYvI/b65DC/t9Sy15+EFpFaos2SkrxxCB/8jRju1+Bxy0itGAoJC51cQCZShLEMKr9zixufM8GrL6rEYEJMP/XUEF7tUA6G1q9srOHaNICE3UakgiBTs8/Vlk/4NTuuZU/Je7C/X07zvyS3EXrSmbSdFQQ5+rhj2EV5ytaCMII40KbOwGVcnThxvSpa3jF73W1YpTJm5WcMNCHwLAmrwy/IAAWOin/Z8TgF32drxmb4FXFpf41E2ixfbtTBjp09+nyT9QOfOieZKtLUJLOu33otiUiQq2zPd57HjZ2QaXTo7DCdR3XmHyolrYyBxqiPAnqfl/YbPiiCGOmPHisClNix+ykltdwoovYQdXMr8DBO0Djw3eJPs6okEHSc5Odo8RkBlxA16aPg+lmUOvR4SvIQe8xb3DCpMZa8unM6xa8CY3Ut/tcYLFR5riUyXM3NSsLHkwLPkQsz/mduB3RLdONiQGCC93CQD3ZBfjfvqsFDKJLiCm76AbyOvgwZ2gHi/e9TnQTtN/NH8KbiubOuTiI5znTwR1i6MNjWyVCy3enpmHf5g+F2cYLje0n4FtBFagsK5XpU5PNU4xzm+iCZ/NoK+nCHECwCoBgBaQOyCi6EQor1ENBHmQkoGD7+Wz3TTdbewJ+Bn6tlUvRNkQ2heLOkjh/7mrWSNKCYMz4nz0q3IW5ucUwMk5XiE//THakzYXKKzZVWlkJBudrOgetZ5GOwlvKIa94ALJiUsEPuRx4DWmsE6CRQg0kxcAQuSScIYRc9UMbKq+Etk0XwHqtI87g7xgQX5hfxRopM8/1lqPrgw+K0Dhq1hFPlFRjfnZ+SyfFY1k33ZY8RajgpxPZyis3NRc7t4kDkG6xePCTxjqHPnJwbnoegq1OrcUq4unIivJUxwiiDzTt5Wa9mhk2DvkXVwe4OO/bxqz8OS0OeCGOvYTrJc6z6EsIxOPULvzcvLQ1OPHT5h3ODONMDLSTGNW8OPiKlyfX6wJL0NC7H84O/C6p5NptfYI2bAYKOzfcdvuwuNB8iTWcZcdm8TMspM+ITtoUrGA1X0kmAwAucFH8/yK5ttJPhC2F1D8QmZhPxriYVyjmPH0jHmaFn/eXI+jTHpFEQukSiwoxb9Ss6J5CbOXGUxYU1iBP6PwRiZL+ghxvh3MC37XLQCQCNMBgI0LVTYrZUQYzg66uvCGqx1nTVxkQqhLCaXkATTZZCQ48J6BnST5+w9MebdwweZoHK/PXohMCvQnMvybXEMjgYnxmoxYL/lK2jyHwt/HnOPGgouF10BmhriFYfp9bqhqLpAOAGxeqrI9JZo/4rbjDYa6BobyVIQ3CIi+3v3dtIA1SUhwKBsUAOro65uYxDRyG/xX0+Zjbk4+I0ZMA+Etd6dmHTr/TKfPP0Q3G6h5/Tts3DTZaD2Lo/rGaToAsHOr5tIFaul3W1ik1Cl8CCFJqBvOApYzFP192TSNxUfSX/DQNX7Z0Yh9IR9WmgsYzuZoXxWkQg74XJo1tjDcTaXwD5dMw5L8oovMXl+XuMshrxtPdp5FgKSabqms2B0OtYkovyJfRB9Ly+wT0ZBagEcRzXQdSenPC8tSlsTJQJSFv93ZjHeYwMQjUWyeOhfz8wp6XZKuJUXPRyS21eXTMK+P7ZPN00PueJIkWUNZxPzTBmBv/Sn1pVg3nFkkvDRMJpkLaBUf6bycIKyhfy4tLGU0SJ3Haw9V8NBToxpmmS97SHIU4goY8dyM+ewU9WaDwgEChFkXKon08v2vtJzGByFvbxYoT5XJdWnIo6yo2a2GStP+WUByT+izAgFBJZEaqMlCkpkpgaH1GyX0CW/z4S9Il0f+9nPBAanvpUCisNcZLHhkymxUWLK0a1O5lFiP8MUbbY3YEXDDYCJ56UVWGsJrGC07d6SDcI36MTlNMIlp2osEdS2wy1/JwUr6KdHQIw7vV2JxzFFMWFVciauY4eUzt7Aw/Ok/zQmzdvAT6LZwAO+Q9fdHAlDp9yMVnkvsFADqCMAlPSjZL1MiECMQX7tUNNcPIuGjJVgIxHyTBVdyl6iKDRGLwm03Ct9F4jwd9OEYI4f8XoCJxYV2epqa79NYLQE4vJV3rxnpesft+j630BotAqjUDNIwpXuZCVGEaosKWFqmp5Ndn5uMRPhek91GAGrvgaK+M24CjWbiBBB6NTXQpeTJUc1s9P9pEd6gpajKvWIBhUw0nPqUo1nvuN3T51LJGKU/yx+p1vsWK0xlys8r0uaZdG4wbogmTEzz3zPr6r/RAFjafqjMEDScJZiT9kcTY4kJDcurmLNm75k6z95vSd8+X/t90s0OCUhj+WWTbS6hVVLn3burF7/XxyIXlsiQuJ68MuzP5iabUCNaT6qfzemTLGs68hhN5PlJSYojkvTii4X0WOk8uaf66mcTzyRtmfS5w294svgSvnPS3ErhnewlrN09Y9GHAxeVsmd0U9PRIqMa38AYvI7kmKI1pKe7w7eeJgINWnJYMSi/ikJ5Ou0fTw9cqABhUGPfpYjfYzuPP59XKpk4JakdRgPCGAPI3J6Wrv18nqvZFVWMnwz38/n/B3wpZStXmMuRAAAAAElFTkSuQmCC', '00000000-0000-0000-0000-000000000002');

--Insert für Encounter

--Narrative
INSERT INTO `n_narrative` (`id`, `n_status`, `n_div`) VALUES ('00000000-0000-0000-1113-000000000000', 'generated', '<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative</b></p><div style=\"display: inline-block; background-color: --d9e0e7; padding: 6px; margin: 4px; border: 1px solid --8da1b4; border-radius: 5px; line-height: 60%\"><p style=\"margin-bottom: 0px\">Resource &quot;00000000-0000-0000-1116-000000000000&quot; </p></div><p><b>identifier</b>: id: Encounter_Roel_20130404 (TEMP)</p><p><b>status</b>: completed</p><p><b>class</b>: ambulatory (Details: http://terminology.hl7.org/CodeSystem/v3-ActCode code AMB = ''ambulatory'', stated as ''ambulatory'')</p><p><b>type</b>: Consultation <span style=\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\"> (<a href=\"https://browser.ihtsdotools.org/\">SNOMED CT</a>--11429006)</span></p><p><b>priority</b>: Normal <span style=\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\"> (<a href=\"https://browser.ihtsdotools.org/\">SNOMED CT</a>--17621005)</span></p><p><b>subject</b>: <a href=\"patient-00000000-0000-0000-1116-000000000000.html\">Patient/00000000-0000-0000-1116-000000000000: Roel</a> &quot;Roel&quot;</p><h3>Participants</h3><table class=\"grid\"><tr><td>-</td><td><b>Actor</b></td></tr><tr><td>*</td><td><a href=\"practitioner-00000000-0000-0000-1116-000000000000.html\">Practitioner/00000000-0000-0000-1116-000000000000</a> &quot;Dokter Bronsig&quot;</td></tr></table><p><b>serviceProvider</b>: <a href=\"organization-00000000-0000-0000-1116-000000000000.html\">Organization/00000000-0000-0000-1116-000000000000</a> &quot;Artis University Medical Center (AUMC)&quot;</p></div>');
--Subject
INSERT INTO `re_reference` (`id`, `re_reference`, `re_display`) VALUES ('00000000-0000-0000-1114-000000000000', 'Patient/00000000-0000-0000-1116-000000000000', 'Roel');
--PartOf
INSERT INTO `re_reference` (`id`, `re_reference`) VALUES ('00000000-0000-0000-1115-000000000000', 'Encounter/f203');
--Encounter
INSERT INTO `en_encounter` (`id`, `en_status`, `dr_n_id`, `en_re_id`, `en_re_partof_id`) VALUES ('00000000-0000-0000-1116-000000000000', 'finished', '00000000-0000-0000-1113-000000000000', '00000000-0000-0000-1114-000000000000', '00000000-0000-0000-1115-000000000000');
--Identifier
INSERT INTO `i_identifier` (`id`, `i_use`, `i_value`, `i_en_id`) VALUES ('00000000-0000-0000-1117-000000000000', 'temp', 'Encounter_Roel_20130404', '00000000-0000-0000-1116-000000000000');
INSERT INTO `cc_codeableconcept` (`id`, `cc_en_id`) VALUES ('00000000-0000-0000-1118-000000000000', '00000000-0000-0000-1116-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_display`, `co_cc_id`) VALUES ('00000000-0000-0000-1119-000000000000', 'http://snomed.info/sct', '11429006', 'Consultation', '00000000-0000-0000-1118-000000000000');
--Participant
INSERT INTO `re_reference` (`id`, `re_reference`) VALUES ('00000000-0000-0000-1120-000000000000', 'Practitioner/00000000-0000-0000-1116-000000000000');
INSERT INTO `par_participant` (`id`, `par_re_id`) VALUES ('00000000-0000-0000-1121-000000000000', '00000000-0000-0000-1120-000000000000');
INSERT INTO `cc_codeableconcept` (`id`, `cc_text`, `cc_par_id`) VALUES ('00000000-0000-0000-1122-000000000000', 'Actor', '00000000-0000-0000-1121-000000000000');
--Diagnosis
INSERT INTO `re_reference` (`id`, `re_display`) VALUES ('00000000-0000-0000-1123-000000000000', 'Complications from Roel''s TPF chemotherapy on January 28th, 2013');
INSERT INTO `cc_codeableconcept` (`id`) VALUES ('00000000-0000-0000-1124-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_display`, `co_cc_id`) VALUES ('00000000-0000-0000-1125-000000000000', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', 'AD', 'Admission diagnosis', '00000000-0000-0000-1124-000000000000');
INSERT INTO `dia_diagnosis` (`id`, `dia_re_id`, `dia_cc_id`, `dia_rank`, `dia_en_id`) VALUES ('00000000-0000-0000-1126-000000000000', '00000000-0000-0000-1123-000000000000', '00000000-0000-0000-1124-000000000000', '2', '00000000-0000-0000-1116-000000000000');
--Diagnosis
INSERT INTO `re_reference` (`id`, `re_display`) VALUES ('00000000-0000-0000-1127-000000000000', 'The patient is treated for a tumor');
INSERT INTO `cc_codeableconcept` (`id`) VALUES ('00000000-0000-0000-1128-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_display`, `co_cc_id`) VALUES ('00000000-0000-0000-1129-000000000000', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', 'CC', 'Chief complaint', '00000000-0000-0000-1128-000000000000');
INSERT INTO `dia_diagnosis` (`id`, `dia_re_id`, `dia_cc_id`, `dia_rank`, `dia_en_id`) VALUES ('00000000-0000-0000-1130-000000000000', '00000000-0000-0000-1127-000000000000', '00000000-0000-0000-1128-000000000000', '1', '00000000-0000-0000-1116-000000000000');
--Appointment
INSERT INTO `re_reference` (`id`, `re_reference`, `re_en_appointment_id`) VALUES ('00000000-0000-0000-1131-000000000000', 'Appointment/example', '00000000-0000-0000-1116-000000000000');
--StatusHistory
INSERT INTO `pe_period` (`id`, `pe_start`) VALUES ('00000000-0000-0000-1132-000000000000', '2013-03-08');
INSERT INTO  `sh_statushistory` (`id`, `sh_status`, `sh_pe_id`, `sh_en_id`) VALUES ('00000000-0000-0000-1133-000000000000', 'inprogress', '00000000-0000-0000-1132-000000000000', '00000000-0000-0000-1116-000000000000');

--Insert für --Condition

--Narrative
INSERT INTO `n_narrative` (`id`, `n_status`, `n_div`) VALUES ('00000000-0000-0000-1900-000000000000', 'generated', '<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative</b></p><div style=\"display: inline-block; background-color: --d9e0e7; padding: 6px; margin: 4px; border: 1px solid --8da1b4; border-radius: 5px; line-height: 60%\"><p style=\"margin-bottom: 0px\">Resource &quot;00000000-0000-0000-1116-000000000000&quot; </p></div><p><b>identifier</b>: id: Encounter_Roel_20130404 (TEMP)</p><p><b>status</b>: completed</p><p><b>class</b>: ambulatory (Details: http://terminology.hl7.org/CodeSystem/v3-ActCode code AMB = ''ambulatory'', stated as ''ambulatory'')</p><p><b>type</b>: Consultation <span style=\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\"> (<a href=\"https://browser.ihtsdotools.org/\">SNOMED CT</a>--11429006)</span></p><p><b>priority</b>: Normal <span style=\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\"> (<a href=\"https://browser.ihtsdotools.org/\">SNOMED CT</a>--17621005)</span></p><p><b>subject</b>: <a href=\"patient-00000000-0000-0000-1116-000000000000.html\">Patient/00000000-0000-0000-1116-000000000000: Roel</a> &quot;Roel&quot;</p><h3>Participants</h3><table class=\"grid\"><tr><td>-</td><td><b>Actor</b></td></tr><tr><td>*</td><td><a href=\"practitioner-00000000-0000-0000-"2000"-000000000000.html\">Practitioner/00000000-0000-0000-2000-000000000000</a> &quot;Dokter Bronsig&quot;</td></tr></table><p><b>serviceProvider</b>: <a href=\"organization-00000000-0000-0000-2000-000000000000.html\">Organization/00000000-0000-0000-2000-000000000000</a> &quot;Artis University Medical Center (AUMC)&quot;</p></div>');
--ClinicalStatus
INSERT INTO `cc_codeableconcept` (`id`) VALUES ('00000000-0000-0000-1901-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_cc_id`) VALUES ('00000000-0000-0000-1902-000000000000', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', 'resolved', '00000000-0000-0000-1901-000000000000');
--VerificationStatus
INSERT INTO `cc_codeableconcept` (`id`) VALUES ('00000000-0000-0000-1903-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_cc_id`) VALUES ('00000000-0000-0000-1904-000000000000', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', 'confirmed', '00000000-0000-0000-1903-000000000000');
--Serverity
INSERT INTO `cc_codeableconcept` (`id`) VALUES ('00000000-0000-0000-1905-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_cc_id`) VALUES ('00000000-0000-0000-1906-000000000000', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', 'Mild', '00000000-0000-0000-1905-000000000000');
--Code
INSERT INTO `cc_codeableconcept` (`id`) VALUES ('00000000-0000-0000-1907-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_cc_id`) VALUES ('00000000-0000-0000-1908-000000000000', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', 'Fever', '00000000-0000-0000-1907-000000000000');
--Reference Subject
INSERT INTO `re_reference` (`id`, `re_reference`, `re_display`) VALUES ('00000000-0000-0000-1909-000000000000', 'Patient/f201', 'Roel');
--Reference Encounter
INSERT INTO `re_reference` (`id`, `re_reference`) VALUES ('00000000-0000-0000-1910-000000000000', 'Encounter/f201');
--Condition
INSERT INTO `con_condition` (`id`, `con_cc_clinstat_id`, `con_cc_veristat_id`, `con_cc_serverity_id`, `con_cc_code_id`, `con_re_subject_id`, `con_re_encounter_id`, `dr_n_id`) VALUES ('00000000-0000-0000-2000-000000000000', '00000000-0000-0000-1901-000000000000', '00000000-0000-0000-1903-000000000000', '00000000-0000-0000-1905-000000000000', '00000000-0000-0000-1907-000000000000', '00000000-0000-0000-1909-000000000000', '00000000-0000-0000-1910-000000000000', '00000000-0000-0000-1900-000000000000');
--Identifier
INSERT INTO `i_identifier` (`id`, `i_use`, `i_value`, `i_con_id`) VALUES ('00000000-0000-0000-1911-000000000000', 'temp', 'Condition_Roel_20130404', '00000000-0000-0000-2000-000000000000');
--Category (Codeableconcept)
INSERT INTO `cc_codeableconcept` (`id`, `cc_con_category_id`) VALUES ('00000000-0000-0000-1912-000000000000', '00000000-0000-0000-2000-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_cc_id`) VALUES ('00000000-0000-0000-1913-000000000000', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', '3863301', '00000000-0000-0000-1912-000000000000');
--BodySite
INSERT INTO `cc_codeableconcept` (`id`, `cc_con_bodysite_id`) VALUES ('00000000-0000-0000-1914-000000000000', '00000000-0000-0000-2000-000000000000');
INSERT INTO `co_coding` (`id`, `co_system`, `co_code`, `co_cc_id`) VALUES ('00000000-0000-0000-1915-000000000000', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', '5502131', '00000000-0000-0000-1914-000000000000');

--Insert für RiskAssessment

--Reference
INSERT INTO `re_reference` (`id`, `re_reference`, `re_display`) VALUES ('88800000-0000-0000-0000-000000000000', 'RiskAssessment/f201', 'Risk');
--RiskAssessment
INSERT INTO `ra_riskassessment` (`id`, `ra_re_id`, `ra_status`) VALUES ('99900000-0000-0000-0000-000000000000', '88800000-0000-0000-0000-000000000000', 'registered');
--Identifier
INSERT INTO `i_identifier` (`id`, `i_use`, `i_value`, `i_ra_id`) VALUES ('77700000-0000-0000-0000-000000000000', 'temp', 'RiskAssessment_000', '99900000-0000-0000-0000-000000000000');
--Prediction
INSERT INTO `cc_codeableconcept` (`id`) VALUES ('77500000-0000-0000-0000-000000000000');
INSERT INTO `pr_prediction` (`id`, `pr_cc_id`, `pr_prohabilitydecimal`, `pr_prohabilityrange`, `pr_ra_id`) VALUES ('77600000-0000-0000-0000-000000000000', '77500000-0000-0000-0000-000000000000', '10', '50', '99900000-0000-0000-0000-000000000000');

--Insert für Invoice

--Invoice
INSERT INTO `in_invoice` (`id`) VALUES ('01111000-0000-0000-0000-000000000000');
--CodeableConcept
INSERT INTO `cc_codeableconcept` (`id`, `cc_text`) VALUES ('04444000-0000-0000-0000-000000000000', 'Item3');
INSERT INTO `co_coding` (`id`, `co_code`, `co_system`, `co_display`, `co_user_selected`, `co_cc_id`) VALUES ('05555000-0000-0000-0000-000000000000', '28342f', 'https://fhir.cerner.com/ec2458f2-1e24-41c8-b71b-0e701af7583d/codeSet/29600', 'MD', true, '04444000-0000-0000-0000-000000000000');
--Reference
INSERT INTO `re_reference` (`id`, `re_reference`, `re_display`) VALUES ('03333000-0000-0000-0000-000000000000', 'Invoice/01111000-0000-0000-0000-000000000000', 'Item3');
--LineItem
INSERT INTO `li_lineitem` (`id`, `li_type`, `li_re_id`, `li_cc_id`, `li_in_id`) VALUES ('02222000-0000-0000-0000-000000000000', 'discount', '03333000-0000-0000-0000-000000000000', '04444000-0000-0000-0000-000000000000', '01111000-0000-0000-0000-000000000000');


--Insert für Bundle


--Identifier
INSERT INTO `i_identifier` (`id`, `i_use`, `i_value`) VALUES ('00000000-0000-0000-0000-999999999998', 'temp', 'Bundle_01');
--Bundle
INSERT INTO `bu_bundle` (`id`, `bu_i_id`, `bu_total`) VALUES ('00000000-0000-0000-0000-999999999997', '00000000-0000-0000-0000-999999999998', '10');
--Link
INSERT INTO `lk_link` (`id`, `lk_relation`, `lk_uri`, `lk_bu_id`) VALUES ('00000000-0000-0000-0000-999999999999', 'self', 'link', '00000000-0000-0000-0000-999999999997');


--Insert für Measure

INSERT INTO `cc_codeableconcept` (`id`, `cc_text`) VALUES ('99920000-0000-0000-0000-000000000000', 'Negative for Chlamydia Trachomatis rRNA');
INSERT INTO `co_coding` (`id`, `co_code`, `co_system`, `co_display`, `co_cc_id`) VALUES ('99960000-0000-0000-0000-000000000000', '260385009', 'http://snomed.info/sct', 'Negative', '99920000-0000-0000-0000-000000000000');
INSERT INTO `co_coding` (`id`, `co_code`, `co_system`, `co_display`, `co_cc_id`) VALUES ('99970000-0000-0000-0000-000000000000', 'NEG', 'https://acme.lab/resultcodes', 'Negative','99920000-0000-0000-0000-000000000000');

INSERT INTO `gr_group` (`id`, `gr_description`, `gr_cc_id`) VALUES ('99950000-0000-0000-0000-000000000000', 'Negative Measure', '99920000-0000-0000-0000-000000000000');

INSERT INTO `n_narrative` (`id`, `n_div`, `n_status`) VALUES ('99980000-0000-0000-0000-000000000000', '<div xmlns=\"http://www.w3.org/1999/xhtml\">Screening for Alcohol Misuse\n <p>This measure example is used to illustrate how a composite measure can be constructured. This measure is one component of the composite measure.</p></div>', 'generated');


INSERT INTO `me_measure` (`id`, `me_version`, `me_date`, `me_url`, `me_gr_id`, `dr_n_id`) VALUES ('component-a-example', '4.6.0', '2014-03-08', 'http://www.measure/1','99950000-0000-0000-0000-000000000000', '99980000-0000-0000-0000-000000000000');


--Identifier
INSERT INTO `i_identifier` (`id`, `i_use`, `i_value`, `i_me_id`) VALUES ('99910000-0000-0000-0000-000000000000', 'official', 'exclusive-breastfeeding-measure', 'component-a-example');

-- 270423: Inserts für Medication
-- 1:1 Beziehungen mit dem Patienten müssen vor dem Patienten eingefügt werden.
-- 1:n Beziehungen mit dem Patienten müssen nach dem Patienten eingefügt werden

INSERT INTO `cc_codeableconcept` (`id`, `cc_text`) VALUES ('11111001-0000-0000-0000-000000000000', 'Negative for Chlamydia Trachomatis rRNA');
INSERT INTO `co_coding` (`id`, `co_code`, `co_system`, `co_display`, `co_cc_id`) VALUES ('11111002-0000-0000-0000-000000000000', '260385009', 'http://snomed.info/sct', 'Negative', '11111001-0000-0000-0000-000000000000');
INSERT INTO `q_quantity` (`id`, `q_value`, `q_comparator`, `q_unit`, `q_system`, `q_code`) VALUES ('11111003-0000-0000-0000-000000000000', 123, 'comp1', 'unit3', 'SnomedSystem', 'HEX123//ßgh');
INSERT INTO `b_batch`(`id`, `b_Iotnumber`, `b_expirationdate`) VALUES ('11111007-0000-0000-0000-000000000000', '123', '2024-03-08');

INSERT INTO `med_medication` (`id`, `med_status`, `med_cc_id`, `med_q_id`, `med_b_id`) VALUES ('11111000-0000-0000-0000-000000000000', 'active','11111001-0000-0000-0000-000000000000', '11111003-0000-0000-0000-000000000000', '11111007-0000-0000-0000-000000000000');

INSERT INTO `i_identifier` (`id`, `i_use`, `i_value`, `i_med_id`) VALUES ('11111004-0000-0000-0000-000000000000', 'official', 'exclusive-breastfeeding-measure', '11111000-0000-0000-0000-000000000000');

INSERT INTO `cr_codeablereference` (`id`, `cr_cc_id`, `cr_re_id`) VALUES ('11111005-0000-0000-0000-000000000000', null, null);

INSERT INTO `in_ingredient`(`id`, `in_isactive`, `in_cr_id`, `in_ra_id`, `in_cc_id`, `in_q_id`, `in_med_id`) VALUES ('11111006-0000-0000-0000-000000000000', true, '11111005-0000-0000-0000-000000000000', null, null, null, '11111000-0000-0000-0000-000000000000');

-- 2.medication
INSERT INTO `cc_codeableconcept` (`id`, `cc_text`) VALUES ('11111001-0000-0000-0000-000000000001', 'Negative for Chlamydia Trachomatis rRNA');
INSERT INTO `co_coding` (`id`, `co_code`, `co_system`, `co_display`, `co_cc_id`) VALUES ('11111002-0000-0000-0000-000000000001', '260385009', 'http://snomed.info/sct', 'Negative', '11111001-0000-0000-0000-000000000001');
INSERT INTO `q_quantity` (`id`, `q_value`, `q_comparator`, `q_unit`, `q_system`, `q_code`) VALUES ('11111003-0000-0000-0000-000000000001', 123, 'comp1', 'unit3', 'SnomedSystem', 'HEX123//ßgh');
INSERT INTO `b_batch`(`id`, `b_Iotnumber`, `b_expirationdate`) VALUES ('11111007-0000-0000-0000-000000000001', '123', '2024-03-08');

INSERT INTO `med_medication` (`id`, `med_status`, `med_cc_id`, `med_q_id`, `med_b_id`) VALUES ('11111000-0000-0000-0000-000000000001', 'active','11111001-0000-0000-0000-000000000001', '11111003-0000-0000-0000-000000000001', '11111007-0000-0000-0000-000000000001');

INSERT INTO `i_identifier` (`id`, `i_use`, `i_value`, `i_med_id`) VALUES ('11111004-0000-0000-0000-000000000001', 'official', 'exclusive-breastfeeding-measure', '11111000-0000-0000-0000-000000000001');

INSERT INTO `cr_codeablereference` (`id`, `cr_cc_id`, `cr_re_id`) VALUES ('11111005-0000-0000-0000-000000000001', null, null);

INSERT INTO `in_ingredient`(`id`, `in_isactive`, `in_cr_id`, `in_ra_id`, `in_cc_id`, `in_q_id`, `in_med_id`) VALUES ('11111006-0000-0000-0000-000000000001', true, '11111005-0000-0000-0000-000000000001', null, null, null, '11111000-0000-0000-0000-000000000001');

-- MEDICATION ENDE

-- 1:1 obs --
INSERT INTO `re_reference` (`id`, `re_display`, `re_reference`, `re_type`) VALUES ('69690000-0000-0000-0000-000000000004', 'Observation', 'Observation/69910000-0000-0000-0000-000000000001', 'Observation');
INSERT INTO `n_narrative` (`id`, `n_div`, `n_status`) VALUES ('69690000-0000-0000-0000-000000000003', '<div xmlns=\"http://www.w3.org/1999/xhtml\">Sept 17, 2012: Systolic Blood pressure107/60 mmHg (low)</div>', 'generated');
INSERT INTO `obsd_observationdefinition` (`id`, `dr_n_id`) VALUES ('69690000-0000-0000-0000-000000000005', '69690000-0000-0000-0000-000000000003');

-- observation --
INSERT INTO `obs_observation` (`id`, `obs_canonical`, `dr_n_id`, `obs_re_id`, `obs_obsdef_id`) VALUES ('69910000-0000-0000-0000-000000000001', '69690000-0000-0000-0000-000000000002', '69690000-0000-0000-0000-000000000003', '69690000-0000-0000-0000-000000000004', '69690000-0000-0000-0000-000000000005');

-- 1:n obs --
INSERT INTO `tr_triggeredby` (`id`, `tr_re_id`, `obs_tr_id`) VALUES ('69910000-0000-0000-0000-000000000006', '69690000-0000-0000-0000-000000000004', '69910000-0000-0000-0000-000000000001');

-- Insert für Immunization
INSERT INTO `re_reference` (`id`, `re_reference`) VALUES ('77700000-0000-0000-0000-000000000000', 'Immunization');

INSERT INTO `i_immunization` (`id`, `i_expirationdate`, `i_iotnumber`, `i_occurrencedatetime`, `i_occurrencestring`, `i_re_immunization`) VALUES ('99990000-0000-0000-0000-000000000000', '2007-03-03', '4', '1978-10-10', 'elo', '77700000-0000-0000-0000-000000000000');