# gestionActividades
Aplicación para gestionar actividades

Al intentar conectar con la BD de MySQL ocurre un error similar a este:
ERROR 1698 (28000): Acceso denegado para el usuario 'root' @ 'localhost'

Para ello se deberá aplicar la solución planteada en el siguiente link: https://stackoverflow.com/questions/39281594/error-1698-28000-access-denied-for-user-rootlocalhost


$ sudo mysql -u root # I had to use "sudo" since is new installation

mysql> USE mysql;
mysql> UPDATE user SET plugin='mysql_native_password' WHERE User='root';
mysql> FLUSH PRIVILEGES;
mysql> exit;

$ service mysql restart
