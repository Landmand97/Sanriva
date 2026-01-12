CREATE TABLE organisations (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL,
    region VARCHAR(100) NOT NULL,
    specialization VARCHAR(100),
    cvr INT NOT NULL,
    sor_code BIGINT NOT NULL,
    sor_code_parent BIGINT,
    sor_code_root BIGINT,
    PRIMARY KEY (id),
    UNIQUE INDEX udx_sor_code (sor_code)
);

ALTER TABLE organisations
    ADD CONSTRAINT fk_sor_code_parent FOREIGN KEY (sor_code_parent) REFERENCES organisations(sor_code),
    ADD CONSTRAINT fk_sor_code_root FOREIGN KEY (sor_code_root) REFERENCES organisations(sor_code);
