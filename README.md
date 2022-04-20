## Retroalimentacion
Fue un muy buen trabajo. Hiciste uso de la la mayoria de las herramientas y estructuras de codigo aboradadas hasta ahora; y la mejor parte es que no las usaste solo por usarlas, sino que tomaste ventaja de ellas en situaciones donde lo ameritaba.

### Que estuvo bien
- Reusar el PropertyHandler fue una buena eleccion, aunque con los conocimientos adquiridos en el curso pudiste haber mejorado un poco su implementacion.
- Agregar un InputHandler para asegurarte de proporcionar una entrada valida a los componentes y proporcionarle al usuario una retroalimentacion inmediata si los datos capturados no son validos.
- Buen uso de Enums para presentar la lista de opciones y validar la entrada del usuario.
- Excelente trabajo segregando las interfaces que implementan las figuras y la clase Figure2D fue una forma ingeniosa de integrar ambas interfaces.
- Buena aplicacion del principio de Sustitucion Liskov en el FigureService, al retornar un Figure2D.
- Considerando que los archivos se manipulan en hilos separados, fue muy prudente el usar el modificador synchronized en algunos de los metodos del FileHandler.

### Areas de mejora
- Siendo el InputHandler una utility class, cuya funcionalidad se limita a proporcionar metodos estaticos, pudiste haberle creado un constructor privado.
- El switch que controla la secuencia de la aplicacion funciona, pero considero que complica el algoritmo mas de lo necesario. Ademas, al colocar las subetapas de algunos flujos como "TYPE_FIGURE_VALUES" y "TYPE_FILENAME", que ambas son subetapas de "TYPE_FIGURE", se podria decir que no se esta respetando el principiio de Single Responsibility, ni tampoco el de Open-Closed.
- La aplicacion solo escribe en el archivo si este ya existe. No se puede crear archivos nuevos.