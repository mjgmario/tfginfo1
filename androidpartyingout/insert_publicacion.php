<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_user = $_POST['id_user'];
        $descripcion = $_POST['descripcion'];
        $target_dir= $_SERVER['DOCUMENT_ROOT'] . "/publicaciones/";
        $image = $_POST["image"];
        
        
        $query = "INSERT INTO publicacion(id_user,fecha,n_clicks, descripcion) VALUES 
        ('$id_user' ,  NOW(), 0, '$descripcion')";
          
        try {
            $result = $mysql->query($query);
        if($mysql->affected_rows >0){

                $query2 = "SELECT * FROM publicacion
                WHERE id_user = '$id_user'
                ORDER BY fecha desc";        
                $result2 = $mysql->query($query2);
                
                $lista = array();
                while($row =mysqli_fetch_assoc($result2)){
                    $lista[] = $row;
                }
                $id_publicacion = $lista[0]['id'];
                $target_dir = $target_dir .  "$id_publicacion"."." . "jpg";
                $path_bd = "$id_publicacion"."." . "jpg";

                if(file_put_contents($target_dir, base64_decode($image))){   
                    $query3 = "INSERT INTO foto_publicacion(id_publicacion, id_foto_publicacion) VALUES 
                    ('$id_publicacion', '$path_bd')";
                  
                        $result3 = $mysql->query($query3);
            
                    if($mysql->affected_rows >0){
                        $response['error'] = false;
                    
                    }
                    else{
                        $response['error'] = true;
                        $response['message'] = "No se han encontrado filas";
                    
                    }
                   
            
            
                }else{
                    $response['error'] = true;
                    $response['message'] = "No insertado archivo";
                
                }         
                            
            }else{
                $response['error'] = true;
                $response['message'] = "No filas";
              
            }         

            
    
              
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response); 
   
    }   

?>
