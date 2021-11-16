# System aukcyjny

## Wprowadzenie

Specyfikacja wymagań funkcjonalnych w ramach informatyzacji procesu sprzedaży produktów w oparciu o mechanizm aukcyjny.

## Procesy biznesowe

---
<a id="bc1"></a>

### BC1: Sprzedaż aukcyjna

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Opis:** Proces biznesowy opisujący sprzedaż za pomocą mechanizmu aukcyjnego. |

**Scenariusz główny:**

1. [Sprzedający](#ac1) wystawia produkt na aukcję. ([UC1](#uc1))
2. [Kupujący](#ac2) oferuje kwotę za produkt wyższą od aktualnie najwyższej oferty. ([BR1](#br1)) ([UC3](#uc3))
3. [Kupujący](#ac2) wygrywa aukcję ([BR2](#br2)) ([UC2](#uc3))
4. [Kupujący](#ac2) przekazuje należność Sprzedającemu.
5. [Sprzedający](#ac1) przekazuje produkt Kupującemu.

**Scenariusze alternatywne:**

2.A. Oferta Kupującego została przebita, a [Kupujący](#ac2) pragnie przebić aktualnie najwyższą ofertę.

* 2.A.1. Przejdź do kroku 2.

3.A. Czas aukcji upłynął i [Kupujący](#ac2) przegrał aukcję. ([BR2](#br2))

* 3.A.1. Koniec przypadku użycia.

---

## Aktorzy

<a id="ac1"></a>

### AC1: Sprzedający

Osoba oferująca towar na aukcji.

<a id="ac2"></a>

### AC2: Kupujący

Osoba chcąca zakupić produkt na aukcji.

## Przypadki użycia poziomu użytkownika

### Aktorzy i ich cele

[Sprzedający](#ac1):

* [UC1](#uc1): Wystawienie produktu na aukcję.

[Kupujący](#ac2):

* [UC2](#uc2): Wygrywa aukcje.
* [UC3](#uc3): Przebicie ceny maksymalnej.

---
<a id="uc1"></a>

### UC1: Wystawienie produktu na aukcję

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Scenariusz główny:**

1. [Sprzedający](#ac1) zgłasza do systemu chęć wystawienia produktu na aukcję.
2. System prosi o podanie danych produktu i ceny wywoławczej.
3. [Sprzedający](#ac1) podaje dane produktu oraz cenę wywoławczą.
4. System weryfikuje poprawność danych.
5. System informuje o pomyślnym wystawieniu produktu na aukcję.

**Scenariusze alternatywne:**

4.A. Podano niepoprawne lub niekompletne dane produktu.

* 4.A.1. System informuje o błędnie podanych danych.
* 4.A.2. Przejdź do kroku 2.

---

<a id="uc2"></a>

### UC2: Zakończenie aukcji.

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Scenariusz główny:**

1. System informuje o ukończeniu czasu aukcji. ([BR2](#br2))
2. [Kupujący](#ac2) wygrywa aukcję. ([BR2](#br2))
3. [Kupujący](#ac2) przekazuje należność [Sprzedający](#ac1)emu.
4. [Sprzedający](#ac1) przekazuje produkt [Kupujący](#ac2)emu.
5. [Kupujący](#ac2) otrzymuje należność za zakupiony produkt.

2.A. [Kupujący](#ac2) przegrał aukcję. ([BR2](#br2))

2.A.1. System informuje [Kupujący](#ac2)ego o przegraniu aukcji.

---

<a id="uc3"></a>

### UC3: Przebicie ceny przez [kupujący](#ac2)ego.

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Scenariusz główny:**

1. [Kupujący](#ac2) podbija stawkę najwyższą. ([BR2](#br1))
2. System informuje [sprzedający](#ac1)ego o prowadzeniu w transakcji przez [kupujący](#ac2)ego.

---

## Obiekty biznesowe (inaczje obiekty dziedzinowe lub informatycjne)

### BO1: Aukcja

Aukcja jest formą zawierania transakcji kupna-sprzedaży, w której Sprzedający określa cenę wywoławczą produktu,
natomiast Kupujący mogą oferować własną ofertę zakupu każdorazowo proponując kwotę wyższą od aktualnie oferowanej kwoty.
Aukcja kończy się po upływie określonego czasu. Jeśli złożona została co najmniej jedna oferta zakupy produkt nabywa ten
Kupujący, który zaproponował najwyższą kwotę.

### BO2: Produkt

Fizyczny lub cyfrowy obiekt, który ma zostać sprzedany w ramach aukcji.

## Reguły biznesowe

<a id="br1"></a>

### BR1: Złożenie oferty

Złożenie oferty wymaga zaproponowania kwoty wyższej niż aktualnie oferowana o minimum 1,00 PLN.

<a id="br2"></a>

### BR2: Rozstrzygnięcie aukcji

Aukcję wygrywa ten z [Kupujący](#ac2)ch, który w momencie jej zakończenia (upłynięcia czasu) złożył najwyższą ofertę.

## Macierz CRUDL

| Przypadek użycia                                                | Aukcja | Produkt |
| --------------------------------------------------------------- | ------ | ------- |
| [UC1](#uc1): Wystawienia produktu na aukcję                     |    C   |    C    |
| [UC2](#uc2): Zakończenie aukcji przez [kupujący](#ac2)ego       |    D   |    U    |
| [UC3](#uc3): Podbicie ceny przez [kupujący](#ac2)ego            |    U   |    -    |


