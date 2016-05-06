

INSERT INTO usuario (id, estado, fecharegitro, fechaultimamodificacion, nombre, password, username) VALUES (1, 'ACTIVO', NULL, NULL, 'Christian Romero', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'admin');


--
-- TOC entry 2304 (class 0 OID 26953)
-- Dependencies: 206
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO empresa (id, contactociudad, contactodireccion, contactoemail, contactopais, contactotelefono, contactoweb, esretentor, estado, fecharegitro, fechaultimamodificacion, nombre, nombrerepresentantelegal, permitiroperaciondesde, retencionmontominimo, ruc, rucrepresentantelegal, seleccioncentroscostosencompras, sumarinteresalprecio, tasaanualinteres, tasamensualinteres, tasaretencioniva, tasaretencionrenta, tipocosteo, verificarlineacreditocliente, usuarioultimamodificacion) VALUES (1, NULL, 'España', NULL, NULL, 'sdasd', NULL, false, 'ACTIVO', '2016-03-23 17:52:35.736', NULL, 'Mburuvicha Roga', NULL, NULL, NULL, '1234564', NULL, false, false, NULL, NULL, NULL, NULL, NULL, false, NULL);
--INSERT INTO empresa (id, contactociudad, contactodireccion, contactoemail, contactopais, contactotelefono, contactoweb, esretentor, estado, fecharegitro, fechaultimamodificacion, nombre, nombrerepresentantelegal, permitiroperaciondesde, retencionmontominimo, ruc, rucrepresentantelegal, seleccioncentroscostosencompras, sumarinteresalprecio, tasaanualinteres, tasamensualinteres, tasaretencioniva, tasaretencionrenta, tipocosteo, verificarlineacreditocliente, usuarioultimamodificacion) VALUES (2, NULL, 'Artigas', NULL, NULL, '1231231', NULL, false, 'ACTIVO', '2016-03-28 12:25:53.37', NULL, 'TABACOS S.A.', NULL, NULL, NULL, '123456', NULL, false, false, NULL, NULL, NULL, NULL, NULL, false, NULL);


--
-- TOC entry 2292 (class 0 OID 26887)
-- Dependencies: 194
-- Data for Name: estadocivil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO estadocivil (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion) VALUES (1, 'ACTIVO', '2016-03-30 16:42:47.332', NULL, 'Soltero/a', 1, NULL);
INSERT INTO estadocivil (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion) VALUES (2, 'ACTIVO', '2016-03-30 16:42:53.685', NULL, 'Casado/a', 1, NULL);
INSERT INTO estadocivil (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion) VALUES (3, 'ACTIVO', '2016-03-30 16:43:04.657', NULL, 'Divorciado/a', 1, NULL);
INSERT INTO estadocivil (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion) VALUES (4, 'ACTIVO', '2016-03-30 16:43:15.926', NULL, 'Viudo/a', 1, NULL);


--
-- TOC entry 2284 (class 0 OID 26843)
-- Dependencies: 186
-- Data for Name: sucursal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO sucursal (id, ciudad, direccion, estado, fecharegitro, fechaultimamodificacion, nombre, nroestablecimiento, telefono, empresa_id, usuarioultimamodificacion) VALUES (1, NULL, 'XXXX', 'ACTIVO', '2016-03-29 13:18:15.527', NULL, 'Matriz', '002', '4512378', 1, NULL);


--
-- TOC entry 2294 (class 0 OID 26898)
-- Dependencies: 196
-- Data for Name: tipocontacto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipocontacto (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion) VALUES (1, 'ACTIVO', '2016-03-30 16:41:45.332', NULL, 'Persona Física', 1, NULL);
INSERT INTO tipocontacto (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion) VALUES (2, 'ACTIVO', '2016-03-30 16:42:01.198', NULL, 'Persona Jurídica', 1, NULL);


--
-- TOC entry 2288 (class 0 OID 26865)
-- Dependencies: 190
-- Data for Name: tipodocumento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipodocumento (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion) VALUES (1, 'ACTIVO', '2016-03-30 16:42:10.579', NULL, 'CI', 1, NULL);
INSERT INTO tipodocumento (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion) VALUES (2, 'ACTIVO', '2016-03-30 16:42:20.392', NULL, 'DNI', 1, NULL);
INSERT INTO tipodocumento (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion) VALUES (3, 'ACTIVO', '2016-03-30 16:42:28.394', NULL, 'Pasaporte', 1, NULL);



INSERT INTO public.deposito(id, estado, fecharegitro, fechaultimamodificacion, nombre, tipodeposito,usuarioultimamodificacion, version, empresa_id, sucursal_id)
    VALUES (1, 'ACTIVO', now(), null, 'Mburuvicha Roga', 'NORMAL', 'admin', 1, 1, null);

INSERT INTO public.deposito(id, estado, fecharegitro, fechaultimamodificacion, nombre, tipodeposito,usuarioultimamodificacion, version, empresa_id, sucursal_id)
    VALUES (2, 'ACTIVO', now(), null, 'VENTA', 'VENTA', 'admin', 1, 1, null);
INSERT INTO public.deposito(id, estado, fecharegitro, fechaultimamodificacion, nombre, tipodeposito,usuarioultimamodificacion, version, empresa_id, sucursal_id)
    VALUES (3, 'ACTIVO', now(), null, 'COMPRA', 'COMPRA', 'admin', 1, 1, null);
INSERT INTO public.deposito(id, estado, fecharegitro, fechaultimamodificacion, nombre, tipodeposito,usuarioultimamodificacion, version, empresa_id, sucursal_id)
    VALUES (4, 'ACTIVO', now(), null, 'PERDIDA', 'PERDIDA', 'admin', 1, 1, null);


    SELECT pg_catalog.setval('deposito_id_seq', 5, false);
--
-- TOC entry 2286 (class 0 OID 26854)
-- Dependencies: 188
-- Data for Name: contacto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 187
-- Name: contacto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contacto_id_seq', 1, false);


--
-- TOC entry 2302 (class 0 OID 26942)
-- Dependencies: 204
-- Data for Name: ejemplo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 203
-- Name: ejemplo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ejemplo_id_seq', 1, false);


--
-- TOC entry 2290 (class 0 OID 26876)
-- Dependencies: 192
-- Data for Name: ejemploconsucursal; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 191
-- Name: ejemploconsucursal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ejemploconsucursal_id_seq', 1, false);


--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 205
-- Name: empresa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresa_id_seq', 1, true);


--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 193
-- Name: estadocivil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estadocivil_id_seq', 4, true);


--
-- TOC entry 2296 (class 0 OID 26909)
-- Dependencies: 198
-- Data for Name: grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO grupo (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id) VALUES (1, 'ACTIVO', NULL, NULL, 'admin', 1);


--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 197
-- Name: grupo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grupo_id_seq', 1, true);


--
-- TOC entry 2280 (class 0 OID 26659)
-- Dependencies: 182
-- Data for Name: grupo_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 181
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

--SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- TOC entry 2298 (class 0 OID 26920)
-- Dependencies: 200
-- Data for Name: modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO modulo (id, defaultpage, estado, fecharegitro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion) VALUES (1, '/main/configuracion/home.xhtml?idMenu=1', 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Configuración', 'Tickets, Facturas..', NULL);
--INSERT INTO modulo (id, defaultpage, estado, fecharegitro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion) VALUES (2, '/main/configuracion/home.xhtml?idMenu=2', 'INACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Infomes', 'Configuraciones generales', NULL);
--INSERT INTO modulo (id, defaultpage, estado, fecharegitro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion) VALUES (3, '/main/configuracion/home.xhtml?idMenu=3', 'INACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Contabilidad', 'Configuraciones generales', NULL);
--INSERT INTO modulo (id, defaultpage, estado, fecharegitro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion) VALUES (4, '/main/confoguracion/home.xhtml?idMenu=4', 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Finanzas', 'Configuraciones generales', NULL);
INSERT INTO modulo (id, defaultpage, estado, fecharegitro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion) VALUES (5, '/main/stock/home.xhtml?idMenu=5', 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Stock', 'Configuraciones generales', NULL);
--INSERT INTO modulo (id, defaultpage, estado, fecharegitro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion) VALUES (6, '/main/compras/home.xhtml?idMenu=6', 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Compras', 'Configuraciones generales', NULL);
--INSERT INTO modulo (id, defaultpage, estado, fecharegitro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion) VALUES (7, '/main/puntoventa/home.xhtml?idMenu=7', 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Ventas', 'Configuraciones generales', NULL);


--
-- TOC entry 2300 (class 0 OID 26931)
-- Dependencies: 202
-- Data for Name: submenu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO submenu (id, estado, fecharegitro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion) VALUES (1, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-uniE675', 'Configuraciones', 1, NULL);
INSERT INTO submenu (id, estado, fecharegitro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion) VALUES (2, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-uniE675', 'Principal', 5, NULL);
--INSERT INTO submenu (id, estado, fecharegitro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion) VALUES (3, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'fa fa-user', 'Contactos', 1, NULL);
--INSERT INTO submenu (id, estado, fecharegitro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion) VALUES (4, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'fa fa-money', 'Monedas', 1, NULL);
INSERT INTO submenu (id, estado, fecharegitro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion) VALUES (5, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-uniE675', 'Movimientos', 5, NULL);




--
-- TOC entry 2282 (class 0 OID 26832)
-- Dependencies: 184
-- Data for Name: menuitem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (3, 'ACTIVO', NULL, NULL, 'fa fa-search', '/main/stock/home.xhtml', 'Buscar por Código', 2, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (4, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/stock/producto/listado.xhtml', 'Producto', 2, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (1, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/empresa/listado.xhtml', 'Empresa', 1, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (2, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/sucursal/listado.xhtml', 'Sucursal', 1, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (5, 'ACTIVO', NULL, NULL, 'fa fa-user', '/main/configuracion/contacto/listado.xhtml', 'Contacto', 2, NULL);
--INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (5, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/tipoContacto/listado.xhtml', 'Tipo Contacto', 3, NULL);
--INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (6, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/tipoDocumento/listado.xhtml', 'Tipo Documento', 3, NULL);
--INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (7, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/estadoCivil/listado.xhtml', 'Estado Civil', 3, NULL);
--INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (8, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/moneda/listado.xhtml', 'Moneda', 4, NULL);


INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (9, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/stock/unidadMedida/listado.xhtml', 'Unidad de Medida', 1, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (10, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/stock/familia/listado.xhtml', 'Familia', 1, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (12, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/stock/comprobanteStock/listado.xhtml', 'Comprobante de Stock', 2, NULL);
--INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (13, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/stock/loteExistencia/listado.xhtml', 'Lote de Existencia', 5, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (14, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/stock/deposito/listado.xhtml', 'Depósito', 1, NULL);
--INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (15, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/cotizacion/listado.xhtml', 'Cotización', 4, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (16, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/stock/productoUnidadMedida/listado.xhtml', 'Producto X U.M. Conf.', 1, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (17, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/stock/inventario/nuevo.xhtml', 'Inventario Inicial', 5, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion) VALUES (18, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/stock/movimientoStock/listado.xhtml', 'Movimientos', 5, NULL);


--
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 183
-- Name: menuitem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menuitem_id_seq', 19, true);


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 199
-- Name: modulo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('modulo_id_seq', 7, true);


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 201
-- Name: submenu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('submenu_id_seq', 5, true);


--
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 185
-- Name: sucursal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sucursal_id_seq', 1, true);


--
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 195
-- Name: tipocontacto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipocontacto_id_seq', 2, true);


--
-- TOC entry 2325 (class 0 OID 0)
-- Dependencies: 189
-- Name: tipodocumento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipodocumento_id_seq', 3, true);


--
-- TOC entry 2308 (class 0 OID 26978)
-- Dependencies: 210
-- Data for Name: usuario_empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario_empresa (empresa_id, usuarios_id) VALUES (1, 1);
--INSERT INTO usuario_empresa (empresa_id, usuarios_id) VALUES (2, 1);


--
-- TOC entry 2307 (class 0 OID 26973)
-- Dependencies: 209
-- Data for Name: usuario_grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario_grupo (grupos_id, usuarios_id) VALUES (1, 1);


--
-- TOC entry 2326 (class 0 OID 0)
-- Dependencies: 207
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_seq', 1, true);


-- Completed on 2016-03-30 22:02:31

--
-- PostgreSQL database dump complete
--


INSERT INTO tipocomprobantestock (id, estado, fecharegitro, fechaultimamodificacion, nombre,tipo, empresa_id,mostrar) VALUES (1, 'ACTIVO', NULL, NULL, 'Entrada por Compra','ENTRADA', 1,true);
INSERT INTO tipocomprobantestock (id, estado, fecharegitro, fechaultimamodificacion, nombre,tipo, empresa_id,mostrar) VALUES (2, 'ACTIVO', NULL, NULL, 'Salida por Consumo Interno','SALIDA', 1,false);
INSERT INTO tipocomprobantestock (id, estado, fecharegitro, fechaultimamodificacion, nombre,tipo, empresa_id,mostrar) VALUES (3, 'ACTIVO', NULL, NULL, 'Salida por Venta','SALIDA', 1,false);
INSERT INTO tipocomprobantestock (id, estado, fecharegitro, fechaultimamodificacion, nombre,tipo, empresa_id,mostrar) VALUES (4, 'ACTIVO', NULL, NULL, 'Entrada por Ajuste','ENTRADA', 1,true);
INSERT INTO tipocomprobantestock (id, estado, fecharegitro, fechaultimamodificacion, nombre,tipo, empresa_id,mostrar) VALUES (5, 'ACTIVO', NULL, NULL, 'Salida por Ajuste','SALIDA', 1,false);
INSERT INTO tipocomprobantestock (id, estado, fecharegitro, fechaultimamodificacion, nombre,tipo, empresa_id,mostrar) VALUES (6, 'ACTIVO', NULL, NULL, 'Salida por Perdida','SALIDA', 1,false);
INSERT INTO tipocomprobantestock (id, estado, fecharegitro, fechaultimamodificacion, nombre,tipo, empresa_id,mostrar) VALUES (7, 'ACTIVO', NULL, NULL, 'Transferencia','SALIDA', 1,true);


SELECT pg_catalog.setval('tipocomprobantestock_id_seq', 6, true);


CREATE OR REPLACE VIEW public.groups AS 
 SELECT u.username AS userid,
    g.nombre AS groupid
   FROM usuario_grupo ug
     JOIN usuario u ON u.id = ug.usuarios_id
     JOIN grupo g ON g.id = ug.grupos_id
  WHERE u.estado::text = 'ACTIVO'::text;

ALTER TABLE public.groups
  OWNER TO postgres;


  CREATE OR REPLACE VIEW public.users AS 
 SELECT usuario.username AS userid,
    usuario.password
   FROM usuario
  WHERE usuario.estado::text = 'ACTIVO'::text;

ALTER TABLE public.users
  OWNER TO postgres;