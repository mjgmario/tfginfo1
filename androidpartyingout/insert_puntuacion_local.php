<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_user = $_POST['id_user'];
        $id_local = $_POST['id_local'];
        $puntuacion = $_POST['puntuacion'];

        $query2 = "SELECT COUNT(*) as contador FROM puntuacion_local
        WHERE id_user = '$id_user' AND id_local = '$id_local' ";
        $result2 = $mysql->query($query2);

        if($mysql->affected_rows >0){
            
             $lista2 = array();

            while($row =mysqli_fetch_assoc($result2)){
                $lista2[] = $row;           
            }
            $num_veces = $lista2[0]['contador'];
            if($num_veces >= 1){
                $response['muchas'] = true;
    
            }
            else{
                $response['muchas'] = false;
                $query = "INSERT INTO puntuacion_local(id_user,id_local, puntuacion) VALUES 
                ('$id_user' , '$id_local', '$puntuacion')";
                 try {
                    $result = $mysql->query($query);
        
                if($mysql->affected_rows >0){
                    $response['error'] = false;
                
                }
                else{
                    $response['error'] = true;
                    $response['message'] = "No se ha podido ejecutar";
                
                }
                } catch (Exception $e) {
                    $response['error'] = true;
                    $response['message'] = $e->getMessage();
                }

            }
        
        }
        else{
            $response['muchas'] = false;
                $query = "INSERT INTO puntuacion_local(id_user,id_local, puntuacion) VALUES 
                ('$id_user' , '$id_local', '$puntuacion')";
                 try {
                    $result = $mysql->query($query);
        
                if($mysql->affected_rows >0){
                    $response['error'] = false;
                
                }
                else{
                    $response['error'] = true;
                    $response['message'] = "No se ha podido ejecutar";
                
                }
                } catch (Exception $e) {
                    $response['error'] = true;
                    $response['message'] = $e->getMessage();
                }

            }
        
    
        
        $mysql->close();
        echo json_encode($response); 
   
    }   

?>
