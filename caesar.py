import string
abc = string.ascii_lowercase
ABC = string.ascii_uppercase
charFreq = 'zqxjkvbpygfwmucldrhsnioate'

charFreq += charFreq.upper()
#string.ascii
print(charFreq)

def encryptCaesar(offset, text):
    trns = str.maketrans(abc + ABC, abc[offset:] + abc[:offset] + ABC[offset:] + ABC[:offset])
    result = text.translate(trns)
    #print(result)
    return(result)
    
def decryptCaesar(offset, text):
    encryptCaesar(-offset, text)
    
import operator
    
def decryptFrequency(text):
    charDict = {}
    symbols = set()
    for c in text:
        c = c.lower()
        if c in charDict:
            charDict[c] += 1;
        else:
            if ((c >= 'A') and (c <= 'Z')) or ((c >= 'a') and (c <= 'z')):
                charDict[c] = 1;
            else:
                symbols.add(c)
            
    symStr = ''.join(list(symbols))
                
    print(charDict)   
 
    sortedDict = sorted(charDict.items(), key = operator.itemgetter(1))
    sortedChars = ''.join([i[0] for i in sortedDict])
    sortedChars += sortedChars.upper() + symStr
    print(sortedChars)
    
    fChars = charFreq + symStr
    
    print(fChars)
    
    trns = str.maketrans(sortedChars, fChars)
    result = text.translate(trns)
    #print(result)
    return(result)
    
enc = ''    
offset = int(input("Please enter your offset: ")) % 26

with open('thehobbit.txt', 'r', encoding='utf-8') as content_file:
    content = content_file.read()
    enc = encryptCaesar(offset, content)

#decryptCaesar(offset, enc)
3
dec = decryptFrequency(enc)

with open('decrypted.txt', 'w', encoding='utf-8') as content_file:
    content_file.write(dec)