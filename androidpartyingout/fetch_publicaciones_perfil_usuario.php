<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
        //devuelve la lista de ids de publicaciones del usuario
        $id_user = $_POST['id_user'];
                
        $query = "SELECT id, descripcion, fecha FROM publicacion
         WHERE id_user = '$id_user'
         ORDER BY fecha desc";
        
        try {
            $result = $mysql->query($query);
            
            $lista = array();
            while($row =mysqli_fetch_assoc($result)){
                $lista[] = $row;
            }
            $response['lista'] = $lista;
            $response['error'] = false;
            

            $query2 = "SELECT nombre FROM usuarios
            WHERE id = '$id_user' ";

            $result2 = $mysql->query($query2);
            $lista1 = array();
            while($row =mysqli_fetch_assoc($result2)){
                $lista1[] = $row;
            }
            $response['nombre'] = $lista1[0]['nombre'];

            $query3 = "SELECT Contenido FROM descripcion
            WHERE id_user = '$id_user' ";
            $result3 = $mysql->query($query3);
            $lista2 = array();
            while($row =mysqli_fetch_assoc($result3)){
                $lista2[] = $row;
            }
            $response['descripcion'] = $lista2[0]['Contenido'];



            $query4 = "SELECT id_foto_perfil FROM foto_perfil
            WHERE id_user = '$id_user' ";
            $result4 = $mysql->query($query4);
            if($mysql->affected_rows ==0){
                $response['tiene_foto'] = false;
                
            }
            else{
                $response['tiene_foto'] = true;
                $lista3 = array();
                while($row =mysqli_fetch_assoc($result4)){
                    $lista3[] = $row;
                }
    
                $response['id_foto_perfil'] = $lista3[0]['id_foto_perfil'];
    
            }

           
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
    