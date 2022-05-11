<?php


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("db.php");

        $id_user= $_POST['id_user'];
        $query = "SELECT * FROM chats
        INNER JOIN r_pertenece_user_chat ON r_pertenece_user_chat.id_chat = chats.id_chat 
        WHERE r_pertenece_user_chat.id_user = '$id_user'
        ORDER BY fecha_ultimo_mensaje desc";
      try{  
        $result = $mysql->query($query);

    
        $numeros = array();
        $lista = array();
        while($row =mysqli_fetch_assoc($result)){
            $aux_id_chat = $row['id_chat'];
            $query2 = "SELECT fecha_ultimo_acceso FROM tabla_registro_chat_user
            WHERE id_user = '$id_user' AND id_chat = '$aux_id_chat' ";
            $result2 = $mysql->query($query2);
            $lista2 = array();
            while($row2 =mysqli_fetch_assoc($result2)){
                $lista2[] = $row2;
            }
            $aux_fecha = $lista2[0]['fecha_ultimo_acceso'];

            $query3 = "SELECT COUNT(*) AS contador FROM mensajes
            WHERE id_chat = '$aux_id_chat' AND fecha > '$aux_fecha' ";
            $result3 = $mysql->query($query3);
            $lista3 = array();
            while($row3=mysqli_fetch_assoc($result3)){
                $lista3[] = $row3;
            }
            $numeros[] = $lista3[0];

            $lista[] = $row;
        }
        $response['lista'] = $lista;
        $response['numeros'] = $numeros;
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