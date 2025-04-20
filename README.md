# Projekti 2 nga lënda Siguria e të Dhënave

Ky projekt implementon dy algoritme kriptografike si pjesë e lëndës "Siguria e të Dhënave".
Më poshtë është një përshkrim i shkurtër për secilin file:

## Files dhe Përshkrimet

### `Main.java`
- **Përshkrimi**: Pika hyrëse e projektit. Përdor `Scanner` për të lejuar përdoruesin të zgjedhë një nga algoritmet e implementuara (Playfair Cipher ose Disrupted Transposition Cipher) dhe të kryej enkriptim ose dekriptim.

### `PlayFairCipher/Encrypt.java`
- **Përshkrimi**: Implementon algoritmin e enkriptimit Playfair Cipher. Ndërton një matricë 5x5 bazuar në një fjalë kyçe dhe enkripton tekstin e thjeshtë duke përdorur rregullat e Playfair.

### `PlayFairCipher/Decrypt.java`
- **Përshkrimi**: Implementon algoritmin e dekriptimit Playfair Cipher. Përdor të njëjtën matricë 5x5 si procesi i enkriptimit për të dekriptuar tekstin e koduar në tekst të thjeshtë.

### `DisruptedTranspositionCipher/Encrypt.java`
- **Përshkrimi**: Implementon algoritmin e enkriptimit Disrupted Transposition Cipher. Rregullon tekstin e thjeshtë në një matricë bazuar në një çelës dhe lexon kolonat në një rend të caktuar për të gjeneruar tekstin e koduar.

### `DisruptedTranspositionCipher/Decrypt.java`
- **Përshkrimi**: Implementon algoritmin e dekriptimit për Disrupted Transposition Cipher. Përdor matricën e krijuar nga teksti i koduar dhe çelësi për të rikthyer tekstin e thjeshtë duke ndjekur renditjen e kolonave sipas çelësit.


## Shënime
- Projekti demonstron përdorimin e teknikave klasike kriptografike.
- Sigurohuni që teksti i thjeshtë dhe çelësat të ndjekin formatin e pritur për secilin algoritëm.
