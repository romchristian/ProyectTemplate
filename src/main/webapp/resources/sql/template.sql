

INSERT INTO empresa (id, contactociudad, contactodireccion, contactoemail, contactopais, contactotelefono, contactoweb, esretentor, estado, fecharegitro, fechaultimamodificacion, nombre, nombrerepresentantelegal, permitiroperaciondesde, retencionmontominimo, ruc, rucrepresentantelegal, seleccioncentroscostosencompras, sumarinteresalprecio, tasaanualinteres, tasamensualinteres, tasaretencioniva, tasaretencionrenta, tipocosteo, verificarlineacreditocliente, usuarioultimamodificacion_id) VALUES (1, NULL, 'España', NULL, NULL, 'sdasd', NULL, false, 'ACTIVO', '2016-03-23 17:52:35.736', NULL, 'Codas Vuyk S.A', NULL, NULL, NULL, '1234564', NULL, false, false, NULL, NULL, NULL, NULL, NULL, false, NULL);
INSERT INTO empresa (id, contactociudad, contactodireccion, contactoemail, contactopais, contactotelefono, contactoweb, esretentor, estado, fecharegitro, fechaultimamodificacion, nombre, nombrerepresentantelegal, permitiroperaciondesde, retencionmontominimo, ruc, rucrepresentantelegal, seleccioncentroscostosencompras, sumarinteresalprecio, tasaanualinteres, tasamensualinteres, tasaretencioniva, tasaretencionrenta, tipocosteo, verificarlineacreditocliente, usuarioultimamodificacion_id) VALUES (2, NULL, 'Artigas', NULL, NULL, '1231231', NULL, false, 'ACTIVO', '2016-03-28 12:25:53.37', NULL, 'TABACOS S.A.', NULL, NULL, NULL, '123456', NULL, false, false, NULL, NULL, NULL, NULL, NULL, false, NULL);


--
-- TOC entry 2152 (class 0 OID 75311)
-- Dependencies: 184
-- Data for Name: estadocivil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO estadocivil (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion_id) VALUES (1, 'ACTIVO', '2016-03-30 16:42:47.332', NULL, 'Soltero/a', 1, NULL);
INSERT INTO estadocivil (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion_id) VALUES (2, 'ACTIVO', '2016-03-30 16:42:53.685', NULL, 'Casado/a', 1, NULL);
INSERT INTO estadocivil (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion_id) VALUES (3, 'ACTIVO', '2016-03-30 16:43:04.657', NULL, 'Divorciado/a', 1, NULL);
INSERT INTO estadocivil (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion_id) VALUES (4, 'ACTIVO', '2016-03-30 16:43:15.926', NULL, 'Viudo/a', 1, NULL);


--
-- TOC entry 2144 (class 0 OID 75267)
-- Dependencies: 176
-- Data for Name: sucursal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO sucursal (id, ciudad, direccion, estado, fecharegitro, fechaultimamodificacion, nombre, nroestablecimiento, telefono, empresa_id, usuarioultimamodificacion_id) VALUES (1, NULL, 'XXXX', 'ACTIVO', '2016-03-29 13:18:15.527', NULL, 'Matriz', '002', '4512378', 1, NULL);


--
-- TOC entry 2154 (class 0 OID 75322)
-- Dependencies: 186
-- Data for Name: tipocontacto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipocontacto (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion_id) VALUES (1, 'ACTIVO', '2016-03-30 16:41:45.332', NULL, 'Persona Física', 1, NULL);
INSERT INTO tipocontacto (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion_id) VALUES (2, 'ACTIVO', '2016-03-30 16:42:01.198', NULL, 'Persona Jurídica', 1, NULL);


--
-- TOC entry 2150 (class 0 OID 75300)
-- Dependencies: 182
-- Data for Name: tipodocumento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipodocumento (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion_id) VALUES (1, 'ACTIVO', '2016-03-30 16:42:10.579', NULL, 'CI', 1, NULL);
INSERT INTO tipodocumento (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion_id) VALUES (2, 'ACTIVO', '2016-03-30 16:42:20.392', NULL, 'DNI', 1, NULL);
INSERT INTO tipodocumento (id, estado, fecharegitro, fechaultimamodificacion, nombre, empresa_id, usuarioultimamodificacion_id) VALUES (3, 'ACTIVO', '2016-03-30 16:42:28.394', NULL, 'Pasaporte', 1, NULL);


--
-- TOC entry 2148 (class 0 OID 75289)
-- Dependencies: 180
-- Data for Name: contacto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 179
-- Name: contacto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contacto_id_seq', 1, false);


--
-- TOC entry 2156 (class 0 OID 75333)
-- Dependencies: 188
-- Data for Name: ejemploconempresa; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 187
-- Name: ejemploconempresa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ejemploconempresa_id_seq', 1, false);


--
-- TOC entry 2146 (class 0 OID 75278)
-- Dependencies: 178
-- Data for Name: ejemploconempresasucursal; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 177
-- Name: ejemploconempresasucursal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ejemploconempresasucursal_id_seq', 1, false);


--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 193
-- Name: empresa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresa_id_seq', 2, true);


--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 183
-- Name: estadocivil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estadocivil_id_seq', 4, true);


--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 172
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- TOC entry 2158 (class 0 OID 75344)
-- Dependencies: 190
-- Data for Name: modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO modulo (id, defaultpage, estado, fecharegitro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion_id) VALUES (1, '/main/puntoventa/home.xhtml?idMenu=1', 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Punto de Venta', 'Tickets, Facturas..', NULL);
INSERT INTO modulo (id, defaultpage, estado, fecharegitro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion_id) VALUES (2, '/main/configuracion/home.xhtml?idMenu=2', 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Configuración', 'Configuraciones generales', NULL);


--
-- TOC entry 2160 (class 0 OID 75355)
-- Dependencies: 192
-- Data for Name: submenu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO submenu (id, estado, fecharegitro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion_id) VALUES (1, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-uniE675', 'Empresas', 2, NULL);
INSERT INTO submenu (id, estado, fecharegitro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion_id) VALUES (2, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-uniE675', 'Productos', 1, NULL);
INSERT INTO submenu (id, estado, fecharegitro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion_id) VALUES (3, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'fa fa-user', 'Contactos', 2, NULL);


--
-- TOC entry 2142 (class 0 OID 75256)
-- Dependencies: 174
-- Data for Name: menuitem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion_id) VALUES (3, 'ACTIVO', NULL, NULL, 'icon-uniE675', '#', 'Producto', 2, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion_id) VALUES (1, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/empresa/listado.xhtml', 'Empresa', 1, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion_id) VALUES (2, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/sucursal/listado.xhtml', 'Sucursal', 1, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion_id) VALUES (4, 'ACTIVO', NULL, NULL, 'fa fa-user', '/main/configuracion/contacto/listado.xhtml', 'Contacto', 3, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion_id) VALUES (5, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/tipoContacto/listado.xhtml', 'Tipo Contacto', 3, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion_id) VALUES (6, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/tipoDocumento/listado.xhtml', 'Tipo Documento', 3, NULL);
INSERT INTO menuitem (id, estado, fecharegitro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion_id) VALUES (7, 'ACTIVO', NULL, NULL, 'icon-uniE675', '/main/configuracion/estadoCivil/listado.xhtml', 'Estado Civil', 3, NULL);


--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 173
-- Name: menuitem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menuitem_id_seq', 7, true);


--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 189
-- Name: modulo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('modulo_id_seq', 2, true);


--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 191
-- Name: submenu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('submenu_id_seq', 3, true);


--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 175
-- Name: sucursal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sucursal_id_seq', 1, true);


--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 185
-- Name: tipocontacto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipocontacto_id_seq', 2, true);


--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 181
-- Name: tipodocumento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipodocumento_id_seq', 3, true);


--
-- TOC entry 2165 (class 0 OID 75386)
-- Dependencies: 197
-- Data for Name: usuario_empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 195
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_seq', 1, false);


-- Completed on 2016-03-30 17:14:03

--
-- PostgreSQL database dump complete
--

