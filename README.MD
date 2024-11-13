# Paso 1 : Antes de ejecutar el javaaplication debemos de configurar la base de datos en aplication.propperties 
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# spring.datasource.url=jdbc:mysql://localhost:3306/padron
# spring.datasource.username=root
# spring.datasource.password=root

# spring.jpa.hibernate.ddl-auto=update
# spring.jpa.show-sql=true

# Paso 2 definir un index (opcional)
# Paso 3 crear una carpeta llamada entities y dentro un javaclass con la entidad que vamos a crear
# Paso 4 Crear una carpeta llamada repository donde crearemos un archivo de Java pero de Interface esto es para que se pueda hacer el crud de la entidad producto y se le pasa el tipo de dato de la clave primaria

# Paso 5 creamos una carpeta llamada services y dentro creamos nuevamente un archivo de tipo Interface donde haremos lo metodos que todos estos metodos son los que se van a usar en el controlador
# Paso 6 dentro de services creamos tambien una clase llamada ejemplo SociosServiceImpl , esto para poder diferenciarlo , todo esto es para que se pueda hacer el crud de la entidad Socios y se le pasa el tipo de dato de la clave primaria 