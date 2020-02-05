DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS pictures;


CREATE TABLE ads (

  id INT PRIMARY KEY,
  typology VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  house_size INTEGER DEFAULT NULL,
  garden_size INTEGER DEFAULT NULL,
  score INTEGER DEFAULT NULL,
  irrelevant_since DATE DEFAULT NULL,

);

CREATE TABLE pictures (

  id INT PRIMARY KEY,
  url VARCHAR(250) NOT NULL,
  quality VARCHAR(250) NOT NULL,
  ad_id INTEGER NOT NULL,

  FOREIGN KEY (ad_id) REFERENCES ads(id),
);


INSERT INTO ads (id,typology, description, house_size, garden_size, score, irrelevant_since ) VALUES
        (1,'CHALET', 'Este piso es una ganga, compra, compra, COMPRA!!!!!', 300, null, null, null),
        (2,'FLAT', 'Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo', 300, null, null, null),
        (3,'CHALET', '', 300, null, null, null),
        (4,'FLAT', 'Ático céntrico muy luminoso y recién reformado, parece nuevo', 300, null, null, null),
        (5,'FLAT', 'Pisazo,', 300, null, null, null),
        (6,'GARAGE', '', 300, null, null, null),
        (7,'GARAGE', 'Garaje en el centro de Albacete', 300, null, null, null),
        (8,'CHALET', 'Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!', 300, null, null, null);



INSERT INTO pictures (id, url, quality, ad_id) VALUES
        (1,'http://www.idealista.com/Picture/1', 'SD',1) ,
        (2,'http://www.idealista.com/Picture/2', 'HD',1) ,
        (3,'http://www.idealista.com/Picture/3', 'SD',2) ,
        (4,'http://www.idealista.com/Picture/4', 'HD',2) ,
        (5,'http://www.idealista.com/Picture/5', 'SD',2) ,
        (6,'http://www.idealista.com/Picture/6', 'SD',3) ,
        (7,'http://www.idealista.com/Picture/7', 'SD',3) ,
        (8,'http://www.idealista.com/Picture/8', 'HD',4) ;

