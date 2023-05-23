--
-- PostgreSQL database dump
--

--
-- Name: kweet; Type: TABLE; Schema: public
--

-- CREATE TABLE public.kweet (
--     id bigint NOT NULL,
--     message character varying(255) NOT NULL
-- );
--
--
-- ALTER TABLE ONLY public.kweet
--     ADD CONSTRAINT kweet_pkey PRIMARY KEY (id);
--
-- ALTER TABLE public.kweet OWNER TO postgres;

CREATE DATABASE kweet_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Dutch_Netherlands.1252'
    LC_CTYPE = 'Dutch_Netherlands.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Table: public.kweet

-- DROP TABLE IF EXISTS public.kweet;

CREATE TABLE IF NOT EXISTS public.kweet
(
    id bigint NOT NULL,
    message character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT kweet_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;