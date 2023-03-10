PGDMP     -                     z            check_db    15.1    15.1                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16397    check_db    DATABASE     |   CREATE DATABASE check_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE check_db;
                postgres    false            ?            1259    16399    discount_card    TABLE     ?   CREATE TABLE public.discount_card (
    id bigint NOT NULL,
    client_name character varying(255),
    discount_percentage double precision,
    number integer
);
 !   DROP TABLE public.discount_card;
       public         heap    postgres    false            ?            1259    16398    discount_card_id_seq    SEQUENCE     }   CREATE SEQUENCE public.discount_card_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.discount_card_id_seq;
       public          postgres    false    215                       0    0    discount_card_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.discount_card_id_seq OWNED BY public.discount_card.id;
          public          postgres    false    214            ?            1259    16406    product    TABLE     ?   CREATE TABLE public.product (
    id bigint NOT NULL,
    is_promotional smallint,
    price double precision,
    title character varying(255)
);
    DROP TABLE public.product;
       public         heap    postgres    false            ?            1259    16405    product_id_seq    SEQUENCE     w   CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public          postgres    false    217                       0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
          public          postgres    false    216            ?            1259    16413    purchase    TABLE     m   CREATE TABLE public.purchase (
    id bigint NOT NULL,
    amount integer NOT NULL,
    product_id bigint
);
    DROP TABLE public.purchase;
       public         heap    postgres    false            ?            1259    16412    purchase_id_seq    SEQUENCE     x   CREATE SEQUENCE public.purchase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.purchase_id_seq;
       public          postgres    false    219                       0    0    purchase_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.purchase_id_seq OWNED BY public.purchase.id;
          public          postgres    false    218            o           2604    16402    discount_card id    DEFAULT     t   ALTER TABLE ONLY public.discount_card ALTER COLUMN id SET DEFAULT nextval('public.discount_card_id_seq'::regclass);
 ?   ALTER TABLE public.discount_card ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            p           2604    16409 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            q           2604    16416    purchase id    DEFAULT     j   ALTER TABLE ONLY public.purchase ALTER COLUMN id SET DEFAULT nextval('public.purchase_id_seq'::regclass);
 :   ALTER TABLE public.purchase ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219                      0    16399    discount_card 
   TABLE DATA           U   COPY public.discount_card (id, client_name, discount_percentage, number) FROM stdin;
    public          postgres    false    215          
          0    16406    product 
   TABLE DATA           C   COPY public.product (id, is_promotional, price, title) FROM stdin;
    public          postgres    false    217   =                 0    16413    purchase 
   TABLE DATA           :   COPY public.purchase (id, amount, product_id) FROM stdin;
    public          postgres    false    219   ?                  0    0    discount_card_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.discount_card_id_seq', 1, true);
          public          postgres    false    214                       0    0    product_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.product_id_seq', 1, false);
          public          postgres    false    216                       0    0    purchase_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.purchase_id_seq', 1, false);
          public          postgres    false    218            s           2606    16404     discount_card discount_card_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.discount_card
    ADD CONSTRAINT discount_card_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.discount_card DROP CONSTRAINT discount_card_pkey;
       public            postgres    false    215            u           2606    16411    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    217            w           2606    16418    purchase purchase_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.purchase DROP CONSTRAINT purchase_pkey;
       public            postgres    false    219            x           2606    16419 $   purchase fk3s4jktret4nl7m8yhfc8mfrn5    FK CONSTRAINT     ?   ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT fk3s4jktret4nl7m8yhfc8mfrn5 FOREIGN KEY (product_id) REFERENCES public.product(id);
 N   ALTER TABLE ONLY public.purchase DROP CONSTRAINT fk3s4jktret4nl7m8yhfc8mfrn5;
       public          postgres    false    217    219    3189                  x?3??L?/?4?30?4?????? )e?      
   ^  x?=?Kn?0D??)t?%Ym{d?s??(6?`???O??d7??M??H?	??6̚?>?rpgM?)??:3???3~??????1?iDucL4ԝ;??ް`,??9׳??U7?H??)j|??&?U,????j?D]?8??Deo??D?kn??i???㣰???la1??ۘ???R?x'?\?@?I??%???4e?3????c?ɲ?42?{Ƭ?9 ?$?ݸ8?ht??mL?ͲB?C??9?]?M	()?+?*n???ko<?s??:?ޣrr??0I??]<#??{?S?/<?ry?l???GUN2첒?F?A???I9ɱ? &?Y??9??M~f???_g?g?R?An??         s   x??ۍEQB?Y?L6?????_?hL? ,ˤv?T?T4Y?,??6?????????Дvۍ?+e??g?w?ޭ߸?|p??|?k?#CKčU(%LHw?G:0??W?dW?????m     