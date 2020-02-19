# LightCurve

Aplikacja służąca do klasyfikacji jednowymiarowych serii czasowych (light curves) pochodzących z misji 
<a href="https://www.nasa.gov/mission_pages/kepler/overview/index.html">Kepler</a> przy pomocy Feed Forward Neural Network.
Do trenowania modelu wykorzystywane są dane testowe robo-vetera ze wstrzykniętymi tranzytami planetarnymi. 
## Wykorzystane technologie:

- Spring Boot
- Spring Data / JPA
- nom-tam-fits
- commons-math3
- commons-io
- jfreechart
- MySQL (tymczasowo)
- JSP

## Funkcjonalności

- pobieranie plików gzip z adresów wskazanych w skrypcie
- rozpakowywanie i odczytywanie formatu .fits
- zapisywanie w bazie tylko istotnych danych potrzebnych do trenowania modelu
- Feed Forward Neural Network - dane wejściowe reprezentowane jako wektor 
- wielkość i ilość epok pobierana od użytkownika
- klasyfikacja pojedynczych serii znajdujących się w bazie

## W trakci realizacji

- gradient stochastyczny
- statystyczna validacja modelu
- NN jako mikroserwis
- baza noSQL
- możliwość klasyfikowania danych pochodzących z misji TESS
