<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_user = $_POST['id_user'];
        $descripcion = $_POST['descripcion'];
        $query3 = "SELECT * FROM descripcion
        WHERE id_user = '$id_user' ";
        $result3 = $mysql->query($query3);
        if($mysql->affected_rows >0){
            $lista = array();
            while($row =mysqli_fetch_assoc($result3)){
                $lista[] = $row;
            }
            $desc= $lista[0]['Contenido'];
            if($desc!= $descripcion){
            $query = "UPDATE descripcion SET Contenido =  '$descripcion'
            WHERE id_user='$id_user'";
            $result = $mysql->query($query);
            if($mysql->affected_rows >0){
                $response['error'] = false;
            
            }
            else{
                $response['error'] = true;
                $response['message'] = "No se ha podido ejecutar";
            
            }
        }
        else{
            $response['error'] = false;

        }
        }
    
        else{
        $query2 = "INSERT INTO descripcion(id_user, Contenido) VALUES 
        ('$id_user', '$descripcion')";
        $result2 = $mysql->query($query2);
        if($mysql->affected_rows >0){
            $response['error'] = false;
        
        }
        else{
            $response['error'] = true;
            $response['message'] = "No se ha podido ejecutar";
        
        }
        
    }

         
       
        
        
        $mysql->close();
        echo json_encode($response); 
   
    }   

?>
