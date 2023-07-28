--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2023-07-28 13:33:17

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
-- TOC entry 224 (class 1259 OID 239794)
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart (
    id bigint NOT NULL,
    order_id bigint,
    user_id bigint
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 239793)
-- Name: cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cart_id_seq OWNER TO postgres;

--
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 223
-- Name: cart_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cart_id_seq OWNED BY public.cart.id;


--
-- TOC entry 226 (class 1259 OID 239801)
-- Name: cart_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart_product (
    id bigint NOT NULL,
    quantity integer NOT NULL,
    cart_id bigint,
    product_id bigint
);


ALTER TABLE public.cart_product OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 239800)
-- Name: cart_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cart_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cart_product_id_seq OWNER TO postgres;

--
-- TOC entry 3425 (class 0 OID 0)
-- Dependencies: 225
-- Name: cart_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cart_product_id_seq OWNED BY public.cart_product.id;


--
-- TOC entry 210 (class 1259 OID 239352)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 239351)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO postgres;

--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 209
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- TOC entry 212 (class 1259 OID 239361)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    date timestamp(6) without time zone,
    status integer,
    user_id bigint
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 239360)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;

--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 211
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- TOC entry 220 (class 1259 OID 239719)
-- Name: orders_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders_product (
    orders_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.orders_product OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 239723)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    image character varying(255),
    name character varying(255) NOT NULL,
    price double precision NOT NULL,
    category_id bigint,
    supplier_id bigint
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 239722)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 221
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 228 (class 1259 OID 239835)
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review (
    id bigint NOT NULL,
    assessment integer NOT NULL,
    date timestamp(6) without time zone,
    description character varying(255),
    product_id bigint,
    user_id bigint
);


ALTER TABLE public.review OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 239834)
-- Name: review_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.review_id_seq OWNER TO postgres;

--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 227
-- Name: review_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.review_id_seq OWNED BY public.review.id;


--
-- TOC entry 214 (class 1259 OID 239387)
-- Name: supplier; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.supplier (
    id bigint NOT NULL,
    cnpj character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.supplier OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 239386)
-- Name: supplier_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.supplier_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.supplier_id_seq OWNER TO postgres;

--
-- TOC entry 3430 (class 0 OID 0)
-- Dependencies: 213
-- Name: supplier_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.supplier_id_seq OWNED BY public.supplier.id;


--
-- TOC entry 216 (class 1259 OID 239396)
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_role (
    id bigint NOT NULL,
    role character varying(255)
);


ALTER TABLE public.user_role OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 239395)
-- Name: user_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_role_id_seq OWNER TO postgres;

--
-- TOC entry 3431 (class 0 OID 0)
-- Dependencies: 215
-- Name: user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_role_id_seq OWNED BY public.user_role.id;


--
-- TOC entry 218 (class 1259 OID 239403)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    cep character varying(255),
    cpf character varying(255),
    create_at timestamp(6) without time zone,
    email character varying(255),
    house_number bigint NOT NULL,
    image character varying(255),
    login character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 239402)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3432 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 219 (class 1259 OID 239411)
-- Name: users_userrole; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_userrole (
    users_id bigint NOT NULL,
    userrole_id bigint NOT NULL
);


ALTER TABLE public.users_userrole OWNER TO postgres;

--
-- TOC entry 3218 (class 2604 OID 239797)
-- Name: cart id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart ALTER COLUMN id SET DEFAULT nextval('public.cart_id_seq'::regclass);


--
-- TOC entry 3219 (class 2604 OID 239804)
-- Name: cart_product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_product ALTER COLUMN id SET DEFAULT nextval('public.cart_product_id_seq'::regclass);


--
-- TOC entry 3212 (class 2604 OID 239355)
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- TOC entry 3213 (class 2604 OID 239364)
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- TOC entry 3217 (class 2604 OID 239726)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 3220 (class 2604 OID 239838)
-- Name: review id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review ALTER COLUMN id SET DEFAULT nextval('public.review_id_seq'::regclass);


--
-- TOC entry 3214 (class 2604 OID 239390)
-- Name: supplier id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier ALTER COLUMN id SET DEFAULT nextval('public.supplier_id_seq'::regclass);


--
-- TOC entry 3215 (class 2604 OID 239399)
-- Name: user_role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role ALTER COLUMN id SET DEFAULT nextval('public.user_role_id_seq'::regclass);


--
-- TOC entry 3216 (class 2604 OID 239406)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3414 (class 0 OID 239794)
-- Dependencies: 224
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cart (id, order_id, user_id) VALUES (4, NULL, 2);


--
-- TOC entry 3416 (class 0 OID 239801)
-- Dependencies: 226
-- Data for Name: cart_product; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3400 (class 0 OID 239352)
-- Dependencies: 210
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category (id, description, name) VALUES (1, 'Dar banho', 'Banho');
INSERT INTO public.category (id, description, name) VALUES (2, 'Bem Estar do pet', 'Conforto');
INSERT INTO public.category (id, description, name) VALUES (3, 'Comer', 'Comida');


--
-- TOC entry 3402 (class 0 OID 239361)
-- Dependencies: 212
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (id, date, status, user_id) VALUES (1, '2023-07-26 03:36:53.599', 2, 1);
INSERT INTO public.orders (id, date, status, user_id) VALUES (2, '2023-07-26 03:37:24.751', 2, 1);


--
-- TOC entry 3410 (class 0 OID 239719)
-- Dependencies: 220
-- Data for Name: orders_product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders_product (orders_id, product_id) VALUES (1, 2);
INSERT INTO public.orders_product (orders_id, product_id) VALUES (2, 1);
INSERT INTO public.orders_product (orders_id, product_id) VALUES (2, 2);


--
-- TOC entry 3412 (class 0 OID 239723)
-- Dependencies: 222
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, image, name, price, category_id, supplier_id) VALUES (1, '086e15ae55a35b786f2b1f13e53e80f43.jpg', 'Cachecol para capivara', 200, 2, 3);
INSERT INTO public.product (id, image, name, price, category_id, supplier_id) VALUES (2, '0dog-eyebrow-2.jpg', 'Cosplay para cachorro', 400, 2, 1);


--
-- TOC entry 3418 (class 0 OID 239835)
-- Dependencies: 228
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.review (id, assessment, date, description, product_id, user_id) VALUES (2, 3, NULL, 'a', 2, 1);
INSERT INTO public.review (id, assessment, date, description, product_id, user_id) VALUES (1, 5, NULL, 'bbbbbbbbbbbbb', 1, 1);
INSERT INTO public.review (id, assessment, date, description, product_id, user_id) VALUES (3, 4, '2023-07-28 12:45:06.152', 'bbbbbbbbbbbbb', 1, 1);
INSERT INTO public.review (id, assessment, date, description, product_id, user_id) VALUES (4, 1, '2023-07-28 12:45:10.865', 'a', 1, 1);
INSERT INTO public.review (id, assessment, date, description, product_id, user_id) VALUES (5, 5, '2023-07-28 12:45:47.259', 'a', 1, 1);


--
-- TOC entry 3404 (class 0 OID 239387)
-- Dependencies: 214
-- Data for Name: supplier; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.supplier (id, cnpj, name) VALUES (1, '15.842.502/0001-37', 'Dr. dog');
INSERT INTO public.supplier (id, cnpj, name) VALUES (2, '81.746.438/0001-15', 'Pet clean');
INSERT INTO public.supplier (id, cnpj, name) VALUES (3, '36.250.575/0001-00', 'Cafuné');


--
-- TOC entry 3406 (class 0 OID 239396)
-- Dependencies: 216
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_role (id, role) VALUES (1, 'ADMIN');
INSERT INTO public.user_role (id, role) VALUES (2, 'USER');


--
-- TOC entry 3408 (class 0 OID 239403)
-- Dependencies: 218
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, cep, cpf, create_at, email, house_number, image, login, name, password) VALUES (1, '79073-120', '704.487.644-74', '2023-07-17 22:56:52.116', 'luizfneto201120@gmail.com', 123, '0Luiz_Neto_simple_book_logo_for_an_educational_games_company_whi_18c11da2-ce62-4caa-93e1-9ac2abfe8c7f.png', 'LuizFNeto20', 'Luiz', '$2a$10$osqlrd6YeoKWak0eSOZio.ZJTgJ.Mzre.J72oaoWjvupXephtkxYC');
INSERT INTO public.users (id, cep, cpf, create_at, email, house_number, image, login, name, password) VALUES (2, '79073-120', '517.530.144-68', '2023-07-18 13:17:16.132', 'luiz.neto5@estudante.ifms.edu.br', 234, '015675932_1235354576547649_460712045886482109_o.jpg', 'LuizFNeto21', 'bb', '$2a$10$iNGu71.pKWBRLmWDsYImPODhGL9dIIfAB2UjpyEQIQTyRFqDVLuWG');


--
-- TOC entry 3409 (class 0 OID 239411)
-- Dependencies: 219
-- Data for Name: users_userrole; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users_userrole (users_id, userrole_id) VALUES (1, 1);
INSERT INTO public.users_userrole (users_id, userrole_id) VALUES (2, 2);


--
-- TOC entry 3433 (class 0 OID 0)
-- Dependencies: 223
-- Name: cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cart_id_seq', 6, true);


--
-- TOC entry 3434 (class 0 OID 0)
-- Dependencies: 225
-- Name: cart_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cart_product_id_seq', 6, true);


--
-- TOC entry 3435 (class 0 OID 0)
-- Dependencies: 209
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 3, true);


--
-- TOC entry 3436 (class 0 OID 0)
-- Dependencies: 211
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 2, true);


--
-- TOC entry 3437 (class 0 OID 0)
-- Dependencies: 221
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 2, true);


--
-- TOC entry 3438 (class 0 OID 0)
-- Dependencies: 227
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.review_id_seq', 5, true);


--
-- TOC entry 3439 (class 0 OID 0)
-- Dependencies: 213
-- Name: supplier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.supplier_id_seq', 3, true);


--
-- TOC entry 3440 (class 0 OID 0)
-- Dependencies: 215
-- Name: user_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_role_id_seq', 2, true);


--
-- TOC entry 3441 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- TOC entry 3238 (class 2606 OID 239799)
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


--
-- TOC entry 3244 (class 2606 OID 239806)
-- Name: cart_product cart_product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_product
    ADD CONSTRAINT cart_product_pkey PRIMARY KEY (id);


--
-- TOC entry 3222 (class 2606 OID 239359)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 239366)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 3236 (class 2606 OID 239730)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3246 (class 2606 OID 239840)
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 239394)
-- Name: supplier supplier_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT supplier_pkey PRIMARY KEY (id);


--
-- TOC entry 3228 (class 2606 OID 239419)
-- Name: supplier uk_3vyps0t4nn0j5ivf818ogtepd; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT uk_3vyps0t4nn0j5ivf818ogtepd UNIQUE (cnpj);


--
-- TOC entry 3232 (class 2606 OID 239421)
-- Name: users uk_7kqluf7wl0oxs7n90fpya03ss; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_7kqluf7wl0oxs7n90fpya03ss UNIQUE (cpf);


--
-- TOC entry 3240 (class 2606 OID 239810)
-- Name: cart uk_9emlp6m95v5er2bcqkjsw48he; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT uk_9emlp6m95v5er2bcqkjsw48he UNIQUE (user_id);


--
-- TOC entry 3242 (class 2606 OID 239808)
-- Name: cart uk_dqdpmxpsb72di6d4pgpn0qex1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT uk_dqdpmxpsb72di6d4pgpn0qex1 UNIQUE (order_id);


--
-- TOC entry 3230 (class 2606 OID 239401)
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);


--
-- TOC entry 3234 (class 2606 OID 239410)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3252 (class 2606 OID 239772)
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 3257 (class 2606 OID 239826)
-- Name: cart_product fk2kdlr8hs2bwl14u8oop49vrxi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_product
    ADD CONSTRAINT fk2kdlr8hs2bwl14u8oop49vrxi FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3253 (class 2606 OID 239777)
-- Name: product fk2kxvbr72tmtscjvyp9yqb12by; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk2kxvbr72tmtscjvyp9yqb12by FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);


--
-- TOC entry 3247 (class 2606 OID 239442)
-- Name: orders fk32ql8ubntj5uh44ph9659tiih; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3254 (class 2606 OID 239811)
-- Name: cart fk3xdjlr8pvm6waf3brpkvrcc6d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fk3xdjlr8pvm6waf3brpkvrcc6d FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 3248 (class 2606 OID 239477)
-- Name: users_userrole fk5my3xvuqyi40r711bbi0egopw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_userrole
    ADD CONSTRAINT fk5my3xvuqyi40r711bbi0egopw FOREIGN KEY (userrole_id) REFERENCES public.user_role(id);


--
-- TOC entry 3259 (class 2606 OID 239846)
-- Name: review fk6cpw2nlklblpvc7hyt7ko6v3e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk6cpw2nlklblpvc7hyt7ko6v3e FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3251 (class 2606 OID 239767)
-- Name: orders_product fk7h8a805aormf2mxryf70m1n7a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders_product
    ADD CONSTRAINT fk7h8a805aormf2mxryf70m1n7a FOREIGN KEY (orders_id) REFERENCES public.orders(id);


--
-- TOC entry 3255 (class 2606 OID 239816)
-- Name: cart fkg5uhi8vpsuy0lgloxk2h4w5o6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fkg5uhi8vpsuy0lgloxk2h4w5o6 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3250 (class 2606 OID 239762)
-- Name: orders_product fkhqvk1lcdpap9qym2o94b45mcs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders_product
    ADD CONSTRAINT fkhqvk1lcdpap9qym2o94b45mcs FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3249 (class 2606 OID 239482)
-- Name: users_userrole fkhr8dq0dvaeushcbmjxyntisof; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_userrole
    ADD CONSTRAINT fkhr8dq0dvaeushcbmjxyntisof FOREIGN KEY (users_id) REFERENCES public.users(id);


--
-- TOC entry 3258 (class 2606 OID 239841)
-- Name: review fkiyof1sindb9qiqr9o8npj8klt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fkiyof1sindb9qiqr9o8npj8klt FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3256 (class 2606 OID 239821)
-- Name: cart_product fklv5x4iresnv4xspvomrwd8ej9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_product
    ADD CONSTRAINT fklv5x4iresnv4xspvomrwd8ej9 FOREIGN KEY (cart_id) REFERENCES public.cart(id);


-- Completed on 2023-07-28 13:33:18

--
-- PostgreSQL database dump complete
--

