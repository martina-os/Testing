### **Automatizirano testiranje**

Projekt je napravljen u IntelliJ-u koristeći Selenium, Javu i TestNG.  
Testira se web stranica https://www.bug.hr/ u Google Chrome-u.

---

## **Korištene tehnologije**

Java - programski jezik

WebDriverManager - automatski skida Chrome/Edge/Firefox drivere

Google Chrome

Selenium - okvir za automatizaciju web testiranja

TestNG - okvir za testiranje u Javi

IntelliJ

Apache Maven - alat za automatizaciju build procesa

Surefire plugin - Maven-ov test runner plugin  

JDK - razvojni paket za Javu

---

## **Testovi**

Na početku testiranja se otvara Google Chrome, maksimizira se prozor i kreće 6 testova.

1. googleSearchAndNavigateTest()
- klik na "Odbij sve" gumb za odbijanje Google kolačića
- traženje pojma "bug hr" na Google tražilici
- ostavljeno je korisniku vremena da se riješi reCAPTCHA
- nakon uspješno riješene reCAPTCHA-e klikne se link stranice Bug-a 
- prihvaćanje kolačića
- provjera da li je otvorena točna web stranica

2. recenzijeMobiteliTest()
- hover preko linka za RECENZIJE po pronađenom elementu pomoću xpatha
- hover preko linka za Mobiteli u padajućem izborniku po pronađenom elementu pomoću xpatha te klik na taj link
- provjera da li je otvorena stranica za recenzije mobitela

3. znanostAstronomijaTest()
- hover preko linka za ZNANOST po pronađenom elementu pomoću xpatha
- hover preko linka za Astronomija u padajućem izborniku po pronađenom elementu pomoću xpatha te klik na taj link
- provjera da li je otvorena stranica o astronomiji

4. igreAvantureTest()
- hover preko linka za IGRE po pronađenom elementu pomoću xpatha
- hover preko linka za Avanture u padajućem izborniku po pronađenom elementu pomoću xpatha te klik na taj link
- provjera da li je otvorena točna stranica 

5. forumTest()
- klik na link za FORUM
- provjera da li je otvorena stranica za forum

6. forumTest()
- klik na gumb ikone čovjeka po pronađenom elementu pomoću xpatha
- klik na prijava u padajućem izborniku 
- provjera da li je otvorena stranica za prijavu


Poslije testova se gasi Google Chrome.
