const readline = require('readline-sync');

// Function to calculate (base^exponent) % modulus
function power(base, exponent, modulus) {
  let result = 1;
  base = base % modulus;
  while (exponent > 0) {
    if (exponent % 2 === 1) result = (result * base) % modulus;
    exponent = exponent >> 1; // equivalent to exponent /= 2
    base = (base * base) % modulus;
  }
  return result;
}

// Function to find the greatest common divisor (GCD) of two numbers
function gcd(a, b) {
  if (b === 0) return a;
  return gcd(b, a % b);
}

// Function to generate public and private keys for RSA
function generateKeys(p, q) {
  const n = p * q;
  const phi = (p - 1) * (q - 1);

  // Choose e such that 1 < e < phi and gcd(e, phi) = 1
  let e;
  for (e = 2; e < phi; e++) {
    if (gcd(e, phi) === 1) break;
  }

  // Calculate d, the modular multiplicative inverse of e (mod phi)
  let d;
  for (d = 2; d < phi; d++) {
    if ((e * d) % phi === 1) break;
  }

  return { publicKey: e, privateKey: d, n };
}

// Function to perform RSA encryption
function rsaEncrypt(message, publicKey, n) {
  return power(message, publicKey, n);
}

// Function to perform RSA decryption
function rsaDecrypt(encryptedMessage, privateKey, n) {
  return power(encryptedMessage, privateKey, n);
}

function main() {
  let p = parseInt(readline.question('Enter prime number p: '));
  let q = parseInt(readline.question('Enter prime number q: '));

  // Generate public and private keys
  let { publicKey, privateKey, n } = generateKeys(p, q);

  // Input message to be encrypted
  let message = parseInt(
    readline.question(`Enter message to be encrypted (less than ${n}): `)
  );

  // Perform encryption
  let encryptedMessage = rsaEncrypt(message, publicKey, n);

  // Perform decryption
  let decryptedMessage = rsaDecrypt(encryptedMessage, privateKey, n);

  // Print results
  console.log(`Public Key (e, n): (${publicKey}, ${n})`);
  console.log(`Private Key (d, n): (${privateKey}, ${n})`);
  console.log(`Original Message: ${message}`);
  console.log(`Encrypted Message: ${encryptedMessage}`);
  console.log(`Decrypted Message: ${decryptedMessage}`);
}

// Run the program
main();
