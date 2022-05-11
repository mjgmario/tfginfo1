<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $id_publicacion= $_POST['id_publicacion'];
        $query = "SELECT * FROM publicacion
        WHERE  id = '$id_publicacion' ";
        
      try{  
        $result = $mysql->query($query);
          
        $lista = array();
        while($row =mysqli_fetch_assoc($result)){
            $lista[] = $row;
        }
        $response['fecha'] = $lista[0]['fecha'];
        $response['descripcion'] = $lista[0]['descripcion'];

        $query2 = "SELECT usuarios.nombre AS nombre, usuarios.id AS id FROM usuarios LEFT OUTER JOIN publicacion
        ON publicacion.id_user = usuarios.id
        WHERE publicacion.id = '$id_publicacion' ";

        $result2 = $mysql->query($query2);
        $lista1 = array();
        while($row =mysqli_fetch_assoc($result2)){
            $lista1[] = $row;
        }
        $response['nombre'] = $lista1[0]['nombre'];
       
        $response['id_user'] = $lista1[0]['id'];


        $response['error'] = false;
       }
        catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        $mysql->close();
        echo json_encode($response);
    }
  ?>  