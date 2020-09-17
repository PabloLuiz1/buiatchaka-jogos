--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

-- Started on 2020-09-14 19:53:48

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2903 (class 1262 OID 24576)
-- Name: dbbuiatchaka; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE dbbuiatchaka WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE dbbuiatchaka OWNER TO postgres;

\connect dbbuiatchaka

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 204 (class 1259 OID 126764)
-- Name: bandeira; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bandeira (
    id bigint NOT NULL,
    ativo boolean DEFAULT true NOT NULL,
    data_cadastro date DEFAULT '2020-09-14'::date NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.bandeira OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 126762)
-- Name: bandeira_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bandeira_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bandeira_id_seq OWNER TO postgres;

--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 203
-- Name: bandeira_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bandeira_id_seq OWNED BY public.bandeira.id;


--
-- TOC entry 206 (class 1259 OID 126774)
-- Name: cartao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cartao (
    id bigint NOT NULL,
    ativo boolean DEFAULT true NOT NULL,
    data_cadastro date DEFAULT '2020-09-14'::date NOT NULL,
    data_vencimento date NOT NULL,
    bandeira character varying(255) NOT NULL,
    codigo character varying(255) NOT NULL,
    cpf_titular character varying(255) NOT NULL,
    nome_impresso character varying(255) NOT NULL,
    numero character varying(255) NOT NULL,
    cliente_id bigint
);


ALTER TABLE public.cartao OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 126772)
-- Name: cartao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cartao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cartao_id_seq OWNER TO postgres;

--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 205
-- Name: cartao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cartao_id_seq OWNED BY public.cartao.id;


--
-- TOC entry 208 (class 1259 OID 126787)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id bigint NOT NULL,
    ativo boolean DEFAULT true NOT NULL,
    data_cadastro date DEFAULT '2020-09-14'::date NOT NULL,
    cpf character varying(255) NOT NULL,
    data_nascimento date NOT NULL,
    data_ultimo_login date DEFAULT '2020-09-14'::date NOT NULL,
    email character varying(255) NOT NULL,
    genero character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    rg character varying(255) NOT NULL,
    senha character varying(255) NOT NULL,
    qtd_pedidos integer DEFAULT 0 NOT NULL,
    telefone character varying(255) NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 126785)
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 207
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- TOC entry 210 (class 1259 OID 126802)
-- Name: cupom; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cupom (
    id bigint NOT NULL,
    ativo boolean DEFAULT true NOT NULL,
    data_cadastro date DEFAULT '2020-09-14'::date NOT NULL,
    data_vencimento date NOT NULL,
    codigo character varying(255) NOT NULL,
    valor double precision NOT NULL,
    cliente_id bigint
);


ALTER TABLE public.cupom OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 126800)
-- Name: cupom_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cupom_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cupom_id_seq OWNER TO postgres;

--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 209
-- Name: cupom_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cupom_id_seq OWNED BY public.cupom.id;


--
-- TOC entry 212 (class 1259 OID 126812)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endereco (
    id bigint NOT NULL,
    ativo boolean DEFAULT true NOT NULL,
    data_cadastro date DEFAULT '2020-09-14'::date NOT NULL,
    bairro character varying(255) NOT NULL,
    cep character varying(255) NOT NULL,
    cidade character varying(255) NOT NULL,
    complemento character varying(255),
    estado character varying(255) NOT NULL,
    logradouro character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    numero character varying(255) NOT NULL,
    cliente_id bigint
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 126810)
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.endereco_id_seq OWNER TO postgres;

--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 211
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.endereco_id_seq OWNED BY public.endereco.id;


--
-- TOC entry 202 (class 1259 OID 57897)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 126825)
-- Name: telefone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.telefone (
    id bigint NOT NULL,
    ativo boolean DEFAULT true NOT NULL,
    data_cadastro date DEFAULT '2020-09-14'::date NOT NULL,
    ddd character varying(255) NOT NULL,
    numero character varying(255) NOT NULL,
    cliente_id bigint
);


ALTER TABLE public.telefone OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 126823)
-- Name: telefone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.telefone_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.telefone_id_seq OWNER TO postgres;

--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 213
-- Name: telefone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.telefone_id_seq OWNED BY public.telefone.id;


--
-- TOC entry 2723 (class 2604 OID 126767)
-- Name: bandeira id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bandeira ALTER COLUMN id SET DEFAULT nextval('public.bandeira_id_seq'::regclass);


--
-- TOC entry 2726 (class 2604 OID 126777)
-- Name: cartao id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartao ALTER COLUMN id SET DEFAULT nextval('public.cartao_id_seq'::regclass);


--
-- TOC entry 2729 (class 2604 OID 126790)
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- TOC entry 2734 (class 2604 OID 126805)
-- Name: cupom id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupom ALTER COLUMN id SET DEFAULT nextval('public.cupom_id_seq'::regclass);


--
-- TOC entry 2737 (class 2604 OID 126815)
-- Name: endereco id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco ALTER COLUMN id SET DEFAULT nextval('public.endereco_id_seq'::regclass);


--
-- TOC entry 2740 (class 2604 OID 126828)
-- Name: telefone id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.telefone ALTER COLUMN id SET DEFAULT nextval('public.telefone_id_seq'::regclass);


-- Completed on 2020-09-14 19:53:48

--
-- PostgreSQL database dump complete
--

