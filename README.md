# EncryptDecrypt
Elliptic curve analogue ElGamal encryption scheme requires encoding of the plain message onto
elliptic curve coordinate using Koblitz encoding technique before encryption operation.
The paper proposes a medical image encryption scheme using improved ElGamal encryption technique.
A new ﬁnding has been made in the proposed method where separate calculations for encoding plain message
to elliptic curve coordinate are removed. The algorithm in the improved version of ElGamal encryption scheme
is designed to encrypt medical image where data expansion issue is resolved and execution speed is enhanced. 
The strength of the proposed method is insured through various statistical and security analyses and comparison with other
existing encryption schemes.

An android application is made to encrypt and decrypt texts and numbers using the Elliptic curve analogue ElGamal encryption scheme. There are more applications of it as mentioned in paper and so the further works will also be done on image and audio encryption.

## Preliminaries<br>
#### Elliptic curve over finite field<br>
Let Ep be an elliptic curve equation over a finite field<br>
Ep : y<sup>2</sup> = x<sup>3</sup> + ax + b mod p (1)<br>
where,
a and b are constant with 4a<sup>3</sup> + 27b<sup>2</sup> != 0. <br>
p: prime number.<br>
Coordinates (x, y) ∈ Ep follows certain additive abelian properties.<br>
#### Identity<br>
A point P(x, y) on addition with a point at infinity ∞ is the point itself.<br>
P + ∞ = ∞ + P = P∀P ∈ Ep (2)<br>
#### Negatives<br>
Negative of a point P = (x, y) is given as −P = (x, −y).<br>
#### Point addition<br>
Two points P(x1, y1) and Q(x2, y2) ∈ Ep, adds up to produce a third point R(x3, y3) ∈ Ep.<br>
Mathematically the coordinate of R is computed as:<br>
If x1 , x2,<br>
x3 = {λ<sup>2</sup> − x1 − x2} mod p (3)<br>
y3 = {λ(x1 − x3) − y1} mod p (4)<br>
where,<br>
λ =y2 − y1 / x2 − x1 mod p (5)<br>

#### Point doubling<br>
A point P(x1, y1) ∈ Ep upon addition to itself, generates a point R(x3, y3) ∈ Ep.
If x1 == x2 and y1 == y2 != 0,<br>
x3 = {λ<sup>2</sup> − 2x1} mod p (6)<br>
y3 = {λ(x1 − x3) − y1} mod p (7)<br>
where,<br>
λ =3x1<sup>2</sup>+ a/2y1 mod p (8)<br>


#### Point at infinity<br>
If x1 = x2 but y1 , y2 in point addition case, the two point is said to meet at infinity.<br>
P + Q = ∞ (9)<br>
If the y coordinate is 0 in point doubling case, the point doubling operation is said to meet at
infinity.<br>
P + P = ∞ (10)<br>

#### Point subtraction<br>
Point subtraction is computed as point addition after negating the y2 coordinate.<br>
P(x1, y1) − Q(x2, y2) = P(x1, y1) + Q(x2, −y2) (11)<br>

#### Point multiplication<br>
Multiple times a point is computed as repeated addition<br>
k × P = P + P + ...k times. (12)<br>
Point multiplication operation can be reduced by a combination of point addition and point doubling. To compute k × P with the reduced operation, the following steps are performed.<br>
1. Begin a = k, B = ∞,C = P.<br>
2. If a is even, a = a/2, B = B and C = 2C.<br>
3. If a is odd, a = a − 1, B = B + C and C = C.<br>
4. If a , 0, go back to Step 2.<br>
After completion B = k × P.<br>

### Elliptic curve analogue ElGamal encryption scheme<br>
The strength of Elliptic curve analogue ElGamal encryption scheme (ECAEES) depends on<br>
Elliptic curve discrete logarithmic problem (ECDLP) [19] which is an exponentially difficult<br>
problem with raise in key size. Performing encryption and decryption operation using ECAEES<br>
over a finite field requires computation for encoding plain data to the coordinate of the elliptic
curve.<br>
#### Encryption:<br>
Pc = {kG, (Pm + kPb)} (13)<br>
Pc = {kG, (xc, yc)} (14)<br>
where,<br>
Pc: Cipher text.<br>
G: Generator.<br>
k: Random integer between 1(one) and n − 1 where n is the cyclic order[24] of an elliptic<br>
curve over finite field.<br>
Pm: Plain message represented as elliptic curve coordinate using Koblitz encoding technique.<br>
Pb: Public key of the receiver.<br>
(xc, yc): One of the point in elliptic curve after point addition of Pm and kPb.<br>
All the points {kG, Pm, kPb, (xc, yc)} ∈ Ep<br>

#### Decryption:<br>
Pm = (xc, yc) − nbkG (15)<br>
where,<br>
nb: Receiver’s private key.<br>
### Koblitz encoding technique<br>
Given an elliptic curve over a finite field Ep : y<sup>2</sup> = x<sup>3</sup> + ax + b mod p. Represent the plain
message as an integer m where 0 ≤ m < p/1000 − 1.<br> For 0 ≤ j < 1000, compute xj = 1000m + j
and sj = xj<sup>3</sup> + axj + b mod p. If sj<sup>(</supp−1)/2</sup> ≡ 1 mod p, then sj is a square mod p. For p ≡ 3 mod 4, yj ≡ sj ^ (p+1)/4  mod p.<br>
The message m is embedded as Pm = (xj, yj). m can be recovered by a division operation on x coordinate of Pm and taking the floor value [19]. m = [bxj/1000c].
