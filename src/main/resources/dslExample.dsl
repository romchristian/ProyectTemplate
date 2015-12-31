[condition][]Cuantos {nombre} vendi hoy=$p:Producto( nombre == "{nombre}")
[condition][]Comparar ventas de {nombre1} y {nombre2} de hoy=$p1:Producto( nombre == "{nombre1}")   $p2:Producto( nombre == "{nombre2}")
[condition][]devuelve resultado=$r:Resultado()
[consequence][]Traer resumen=$r.setMensaje("Vendiste " + $p.getCantidadVendida() +" " + $p.getNombre());
[consequence][]Traer comparacion=$r.setMensaje($p1.getNombre() +": " + $p1.getCantidadVendida()+", "+$p2.getNombre()+": " + $p2.getCantidadVendida());