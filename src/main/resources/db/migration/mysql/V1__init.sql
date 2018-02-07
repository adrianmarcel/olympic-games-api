CREATE TABLE `modalidade` (
  `id`        BIGINT(20)  NOT NULL,
  `descricao` VARCHAR(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `local` (
  `id`        BIGINT(20)  NOT NULL,
  `descricao` VARCHAR(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pais` (
  `id`        BIGINT(20)  NOT NULL,
  `descricao` VARCHAR(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `competicao` (
  `id`            BIGINT(20),
  `id_modalidade` BIGINT(20),
  `id_local`      BIGINT(20),
  `dt_inicio`     DATE        NOT NULL,
  `dt_termino`    DATE        NOT NULL,
  `id_pais_1`     BIGINT(20),
  `id_pais_2`     BIGINT(20),
  `etapa`         VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table modalidade
--

ALTER TABLE `modalidade`
  ADD PRIMARY KEY (`id`);
  

--
-- Indexes for table local
--

ALTER TABLE `local`
  ADD PRIMARY KEY (`id`);


--
-- Indexes for table pais
--

ALTER TABLE `pais`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table modalidade
--
ALTER TABLE `modalidade`
 MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table local
--
ALTER TABLE `local`
 MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table pais
--
ALTER TABLE `pais`
 MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table competicao
--
ALTER TABLE `competicao`
  ADD PRIMARY KEY       (`id`),
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT; 

--
-- Indexes for table competicao
--

ALTER TABLE `competicao`
  ADD KEY `FK_MODALIDADE` (`id_modalidade`),
  ADD KEY `FK_LOCAL`      (`id_local`),
  ADD KEY `FK_PAIS_1`     (`id_pais_1`),
  ADD KEY `FK_PAIS_2`     (`id_pais_2`),
  ADD KEY `CK_ETAPA`      (`etapa`),
  ADD CONSTRAINT `FK_MODALIDADE` FOREIGN KEY (`id_modalidade`) REFERENCES `modalidade` (`id`),
  ADD CONSTRAINT `FK_LOCAL`      FOREIGN KEY (`id_local`)      REFERENCES `local`      (`id`),
  ADD CONSTRAINT `FK_PAIS_1`     FOREIGN KEY (`id_pais_1`)     REFERENCES `pais`       (`id`),
  ADD CONSTRAINT `FK_PAIS_2`     FOREIGN KEY (`id_pais_2`)     REFERENCES `pais`       (`id`),
  ADD CONSTRAINT `CK_ETAPA`      CHECK (`etapa` IN (`ELIMINATORIAS`, `OITAVAS`, `QUARTAS`, `SEMIFINAL`, `FINAL`)); 
  
--
--    Inserting data into the table modalidade.
--
INSERT INTO `modalidade` VALUES (1, 'Futebol');
INSERT INTO `modalidade` VALUES (2, 'Boxe');
INSERT INTO `modalidade` VALUES (3, 'Handebol');
INSERT INTO `modalidade` VALUES (4, 'Esgrima');
INSERT INTO `modalidade` VALUES (5, 'Canoagem');
INSERT INTO `modalidade` VALUES (6, 'Ciclismo');
INSERT INTO `modalidade` VALUES (7, 'Trampolim');
INSERT INTO `modalidade` VALUES (8, 'Atletismo');
INSERT INTO `modalidade` VALUES (9, 'Basquete');
INSERT INTO `modalidade` VALUES (10, 'Hipismo');

--
--    Inserting data into the table local.
--
INSERT INTO `local` VALUES (1, 'Kasumigaseki Country Club');
INSERT INTO `local` VALUES (2, 'Estádio Ajinomoto');
INSERT INTO `local` VALUES (3, 'Super Arena de Saitama');
INSERT INTO `local` VALUES (4, 'Enoshima');
INSERT INTO `local` VALUES (5, 'Makuhari Messe');
INSERT INTO `local` VALUES (6, 'Baji Koen');
INSERT INTO `local` VALUES (7, 'Velodromo de Izu');
INSERT INTO `local` VALUES (8, 'Centro Japonês de Ciclismo');
INSERT INTO `local` VALUES (9, 'Estádio de Yokohama');
INSERT INTO `local` VALUES (10, 'Miyagi Stadium');

--
--    Inserting data into the table pais.
--
INSERT INTO `pais` VALUES (1, 'Japão');
INSERT INTO `pais` VALUES (2, 'Brasil');
INSERT INTO `pais` VALUES (3, 'Estados Unidos');
INSERT INTO `pais` VALUES (4, 'Alemanha');
INSERT INTO `pais` VALUES (5, 'Argentina');
INSERT INTO `pais` VALUES (6, 'México');
INSERT INTO `pais` VALUES (7, 'Itália');
INSERT INTO `pais` VALUES (8, 'China');
INSERT INTO `pais` VALUES (9, 'Holanda');
INSERT INTO `pais` VALUES (10, 'Uruguai');