<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");
        ## id_mensaje, contenido, fecha, nombre

        $id_chat = $_POST['id_chat'];
        $query = "SELECT * 
        FROM mensajes 
        LEFT OUTER JOIN usuarios ON mensajes.id_autor=usuarios.id
        WHERE id_chat = '$id_chat'
        ORDER BY fecha desc";
      try{  
        
        $result = $mysql->query($query);
        $lista = array();
        while($row =mysqli_fetch_assoc($result)){
            $lista[] = $row;
        }
        $response['error'] = false;
        $response['lista'] = $lista;


        $query2 = "SELECT nombre
        FROM chats 
        WHERE id_chat = '$id_chat'";
        $result2 = $mysql->query($query2);
        $lista2 = array();

        while($row =mysqli_fetch_assoc($result2)){
            $lista2[] = $row;
        }
        $response['nombre'] = $lista2[0]['nombre'];

       }
        catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        $mysql->close();
        echo json_encode($response);
    }
  ?>  