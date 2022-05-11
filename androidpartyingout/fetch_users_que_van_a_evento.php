<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $id_evento= $_POST['id_evento'];
        $query = "SELECT  id, nombre FROM r_va_a_evento LEFT OUTER JOIN usuarios ON r_va_a_evento.id_user =  usuarios.id
        WHERE  r_va_a_evento.id_evento = '$id_evento' ";

      try{  
        $result = $mysql->query($query);

        $lista = array();
        while($row =mysqli_fetch_assoc($result)){
            $lista[] = $row;
        }
        $response['lista'] = $lista;
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