<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");
        
        $id_chat_local= $_POST['id_chat_local'];
        $id_user= $_POST['id_user'];


        $query = "SELECT * FROM chat_local
        WHERE  id_chat_local = '$id_chat_local' ";
        
      try{  
        $result = $mysql->query($query);

        $lista = array();
        while($row =mysqli_fetch_assoc($result)){
            $lista[] = $row;
        }

        $response['nombre'] = $lista[0]['nombre_chat'];
        $response['descripcion'] = $lista[0]['descripcion'];

        $query2 = "SELECT  COUNT( DISTINCT r_pertenece_user_chat_local.id_user) AS num FROM r_pertenece_user_chat_local LEFT OUTER JOIN r_seguidor_seguido  ON r_pertenece_user_chat_local.id_user
        WHERE  r_pertenece_user_chat_local.id_chat_local  = '$id_chat_local' AND r_seguidor_seguido.id_seguidor = '$id_user' ";
        
        $result = $mysql->query($query2);
        $lista2 = array();
        while($row =mysqli_fetch_assoc($result)){
            $lista2[] = $row;
        }
        $response['num_amigos'] = $lista2[0]['num'];

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