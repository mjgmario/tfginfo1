<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");
        ## id_mensaje, contenido, fecha, nombre

        $id_chat_local = $_POST['id_chat_local'];
        $query = "SELECT * 
        FROM mensajes_chat_local 
        LEFT OUTER JOIN usuarios ON mensajes_chat_local.id_autor=usuarios.id
        WHERE id_chat_local = '$id_chat_local'
        ORDER BY fecha desc";
      try{  
        $result = $mysql->query($query);
  
        $lista = array();
        while($row =mysqli_fetch_assoc($result)){
            $lista[] = $row;
        }
        $response['lista'] = $lista;

        $query2 = "SELECT  nombre_chat
        FROM chat_local 
        WHERE id_chat_local = '$id_chat_local' ";
        
        $result2 = $mysql->query($query2);
  
        $lista2 = array();
        while($row =mysqli_fetch_assoc($result2)){
            $lista2[] = $row;
        }
        $response['nombre_chat'] = $lista2[0]['nombre_chat'];

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