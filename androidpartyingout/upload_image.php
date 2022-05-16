<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $target_dir= $_SERVER['DOCUMENT_ROOT'] . "/perfil/";
    $image = $_POST["image"];
    $id_user = $_POST['id_user'];
    $target_dir = $target_dir .  "$id_user"."." . "jpg";
    $path_bd = "$id_user"."." . "jpg";
    if(file_put_contents($target_dir, base64_decode($image))){   
        $query2 = "SELECT * FROM foto_perfil
        WHERE id_user = '$id_user' ";
        $result2 = $mysql->query($query2);
        if($mysql->affected_rows >0){
            $response['error'] = false;
       
    }else{
        $query = "INSERT INTO foto_perfil(id_foto_perfil, id_user) VALUES 
        ('$path_bd', '$id_user')";
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
           
        }
        else{
            $response['error'] = true;
            $response['message'] = "No se han encontrado filas";
        
        }
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }


    }
                
    }
    else{
        $response['error'] = true;
        $response['message'] = "No uploaded file";
      
    }               
        $mysql->close();
        echo json_encode($response);
    }      

?>