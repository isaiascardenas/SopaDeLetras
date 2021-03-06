# portada

cristian espinoza l (ayudante)

# introduccion

El presente proyecto consiste en crear un programa capaz de identificar, a partir de un listado de palabras, cuales de ellas estan presentes y cuales no en una sopa de letras. La sopa de letras utilizada en este proyecto no es la convencional, ya que las palabras no solo podrán estar en vertical u horizontal si no que las letras pueden estar adyacentes unas de otras en cualquier dirección, esto implica que las letras no pueden estar en diagonal como lo muestra la figura 1, además no se permite utilizar dos veces la misma letra (de la sopa de letras) para estructurar la palabra buscada por lo que la trayectoria de la palabra no puede intersectarse a si misma. Para resolver este problema deberá implentarse el método Busqueda en espacio de soluciones o Busqueda en espacio de estados.

(figura 1)

Las palabras a buscar estarán en un fichero de entrada el cual deberá ser leido. Deberá señalarse cuales de estas palabras fueron encontradas en la sopa de letras y cuales no, para ésto se requiere escribir un fichero de salida como lo señala la figura 2. Se requiere utilizar al menos 3 clases: Una que administre el manejo de archivos, otra que trabaje con la sopa de letras y una clase principal.

Para una mejor comprensión de éste documento es preciso definir algunos conceptos que
serán indispensables en éste proyecto:

* Programación orientada a objetos:
	La programación orientada a objetos es un paradigma de programación que busca emular
	de manera lógica la realidad o el entorno físico. De esta manera el código de un programa
	orientado a objetos encapsula "elementos", que tienen ciertas "características" y
	"funcionalidades", utilizando clases. Una clase es la estructura lógica generalizada para
	objetos con las mismas características y funcionalidades, llamadas atributos y métodos
	respectivamente. Una instancia particular de una clase es llamada Objeto. En general los
	métodos son los que utilizan los atributos de una clase para interactuar con otros objetos o
	bien para realizar una acción en particular.

* Lenguaje de programación Java:
	El lenguaje Java es un lenguaje de programación orientado a objetos, este lenguaje utiliza
	varias clases, predefinidas en él, que interactúan para generar una aplicación. En general
	los tipos de datos, las entradas y salidas y hasta el bloque principal (main) son utilizados
	como clases. La mayor ventaja de éste lenguaje de programación es que fue diseñado para
	que las aplicaciones puedan ejecutarse desde cualquier dispositivo, de manera que no
	necesiten ser compiladas de nuevo.

* Diagramas de flujo:
	Los diagramas de flujo representan un algoritmo de procesamiento a través de bloques y
	flechas, así facilita la comprensión de uno o varios procesos que deben llevarse a cabo para
	lograr un objetivo en específico. En general la sintaxis de los diagramas de flujo consiste en
	dos óvalos denotados como el inicio y el fin del algoritmo, rectángulos que representan un
	proceso específico, rombos que indican bifurcaciones en el proceso general y flechas que
	relacionan las figuras mencionadas anteriormente.

(figura 2)

# Descripcion del problema

Dicho lo anterior, primeramente es necesario extraer las palabras del archivo de entrada para recorrerlas una por una. Por cada palabra buscar la primera letra dentro de la sopa de letras y a partir de ahi buscar la letra que continua en una direccion adyacente como lo ilustra la figura 3. Cabe destacar que por cada letra que tiene la palabra en cuestion tiene 4 letras adyacentes de las cuales, al menos 3, podrían ser la letra que sigue. Por ende es necesario verificar cada una de ellas y determinar si sirve o no sirve y en caso que no sirva volver a la letra anterior para verificar las que faltan.

(figura 3)

Dado que no es posible reutilizar la misma letra (dentro de la sopa de letras ) es necesario denotar de alguna manera las letras que ya han sido verificadas de modo que no vuelvan a ser revisadas. Para efectos de la salida del programa es necesario almacenar las palabras que se encontraron dentro de la sopa de letras y las que no de manera que sea mas eficiente la escritura en el fichero de salida.

# Descripcion de la solucion

Se almacenará la sopa de letras en una matriz creada con ArrayList<String> de manera que se pueda recorrer caracter por caracter, se almacenarán las palabras a buscar en otro ArrayList<String> para recorrerlas una por una y buscarlas dentro de la sopa de letras. Se buscarán coincidencias de la primera letra de la palabra buscada y cada vez que se encuentren se utilizará un algoritmo recursivo para buscar la palabra implementando el método Busqueda por espacio de estados. Éste algoritmo se apoyará en una pila que contiene los estados activos (posibles soluciones) y se iran extrayendo estados desde la pila para realizar una busqueda en profundidad como lo muestra la figura 4.

(figura 4)

## Las clases que interactuan en esta implementación son las siguientes:

* FileManager: Es la clase que administra los ficheros que se leerán y se escribirán, si el archivo de salida no existe la clase lo crea con el contenido correspondiente.

* LetterSoup: Ésta clase se encarga de manipular la sopa de letras para buscar las palabras solicitadas, implementa el método Busqueda en espacio de estados de manera recursiva.

* Application: La clase Application relaciona las dos clases mencionadas anteriormente, instancia un objeto FileManager y un objeto LetterSoup para realizar los objetivos de éste programa.

La interacción de estas clases se ilustran en la figura 5

(figura 5)

La funcionalidad principal se centra en el método depthFirstSearch de la clase LetterSoup, realiza una busqueda en profundidad en la sopa de letras. Para encontrar la palabra dentro de la sopa de letras se genera una pila que guardará los Strings de las direcciones adyacentes para buscar la siguiente letra ademas de el String "back" que servirá para realizar el backtraking en caso de que se haya escogido la direccion equivocada, en el fondo de la pila se guarda el String "notFoud" que indica que la palabra no ha sido encontrada. Se extraen las direcciones desde la pila para buscar la letra correspondiente y encaso de que ese extraiga el String "back" se regresará a la posicion anterior y se buscará en otra dirección, cada vez que cambia la letra a buscar se agregan nuevas direcciones y un "back". Cuando se encuentra la letra se ingresa nuevamente al metodo para buscar la letra que sigue, el  proceso se repite hasta que la pila esté vacia o hasta que se hayan recorrido todas las letras de la palabra.

En caso de que se haya recorrido toda la palabra ésta habrá sido encontrada y se retornara "true", en otro caso se habrá llegado hasta el fondo de la pila en donde estaba el String "notFound" lo que indica que la palabra no ha sido encontrada retornando "false".

Cada palabra en el archivo de entrada (Palabras.in) se busca utilizando el metodo depthFirstSearch y cada palabra encontrada se guarda en el ArrayList findingWords, de manera que al final del proceso se puedan obtener las palabras que no se encontraron removiendo el contenido de findingWords del archivo de entrada. Luego de haber buscado todas las palabras se escribe en el archivo de salida (Solucion.out) las palabras encontradas y las que no.

El proceso descrito se ilustra en el diagrama de flujo de la figura 6.

(figura 6)

# Conclusiones

En resumen el proyecto cumple con las especificaciones requeridas y en el plazo solicitado, a modo de ejemplo se adjuntan 3 ejemplos con diferentes archivos de entrada obteniendo los resultados esperados...


