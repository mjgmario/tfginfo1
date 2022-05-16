# -*- coding: utf-8 -*-
"""
Created on Mon May 16 20:36:41 2022

@author: Usuario
"""
from surprise import KNNWithMeans

from surprise import Dataset
from surprise import Reader
import pandas as pd
import mysql.connector as mysql

## send post request python
import requests
import json
r = requests.post("http://partiny.000webhostapp.com/fetch_user_puntuaciones_local.php", data={})
# And done.
print(r.text)



jsonobject = r.json()
lista = jsonobject.get("lista")
lista_item = []
lista_user = []
lista_puntuaciones = []
for i in range(len(lista)):
    lista_item.append(lista[i].get("id_local"))
    lista_user.append(lista[i].get("id_user"))
    lista_puntuaciones.append(lista[i].get("puntuacion"))


ratings_dict = {
    "item": lista_item,
    "user": lista_user,
    "rating": lista_puntuaciones,
}

df = pd.DataFrame(ratings_dict)
reader = Reader(rating_scale=(1, 10))

# Loads Pandas dataframe
data = Dataset.load_from_df(df[["user", "item", "rating"]], reader)

# To use item-based cosine similarity
sim_options = {
    "name": "cosine",
    "user_based": False,  # Compute  similarities between items
}
algo = KNNWithMeans(sim_options=sim_options)


trainset = data.build_full_trainset()



algo.fit(trainset)



## we want the 4 most rated, we suppose that we have more than 4 elements in the subset
id_user = '9'
masvalorados=[]
ELEM_RECOMMENDATION = 4
conjunto = set(lista_item)
for i in range(ELEM_RECOMMENDATION):
    valormax = -1
    
    for a in conjunto:
        prediction = algo.predict(id_user, a)
        if prediction.est >= valormax:
            elem_max = a
            valormax = prediction.est
    masvalorados.append(elem_max)
    conjunto.remove(elem_max)
print(masvalorados)