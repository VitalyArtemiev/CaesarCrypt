import string
abc = string.ascii_lowercase
ABC = string.ascii_uppercase
charFreq = 'zqxjkvbpygfwmucldrhsnioate'

bigramFreq = ('th', 'he', 'in', 'er', 'an', 're', 'nd' , 'at', 'on', 'nt', 'ha',
'es', 'st', 'en', 'ed', 'to', 'it', 'ou', 'ea', 'hi', 'is', 'or', 'ti', 'as', 
'te', 'et', 'ng', 'of', 'al', 'de', 'se', 'le', 'sa', 'si', 'ar', 've', 'ra', 
'ld', 'ur')

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

def gatherFreq(text: str):
    charDict = {}
    for c in text:
        c = c.lower()
        if c in charDict:
            charDict[c] += 1;
        else:
            if ((c >= 'A') and (c <= 'Z')) or ((c >= 'a') and (c <= 'z')):
                charDict[c] = 1; 
 
    sortedDict = sorted(charDict.items(), key = operator.itemgetter(1))
    sortedChars = ''.join([i[0] for i in sortedDict])
    sortedChars += sortedChars.upper() 
    return sortedChars
    
    
    
def decryptFrequency(text, charFreq):
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
 
    sortedDict = sorted(charDict.items(), key = operator.itemgetter(1))
    sortedChars = ''.join([i[0] for i in sortedDict])
    sortedChars += sortedChars.upper() + symStr
    print("sortedChars")
    print(sortedChars)
    print()
    
    fChars = charFreq + symStr
    print("fChars")
    print(fChars)
    print()
    
    trns = str.maketrans(sortedChars, fChars)
    result = text.translate(trns)
    return(result)
    
def getTranslationAlphabet(text):
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
    
    return str.maketrans(sortedChars, fChars)
    
    
import collections
    
def decryptBigramFrequency(text: str):
    trns = getTranslationAlphabet(text) #for when bigrams fail
    
    charDict = {}
    symbols = set()
    pc: chr = ' ' #previous char
    for c in text:
        if c == ' ':
            continue
        c = c.lower()
        s = pc + c
        if s in charDict:
            charDict[s] += 1;
        else:
            if (pc.isalpha()) and (c.isalpha()):
                charDict[s] = 1;
            else:
                symbols.add(c)
        pc = c
            
    symStr = ''.join(list(symbols))
                
    print(charDict)   
 
    sortedList = sorted(charDict.items(), key = operator.itemgetter(1), reverse = True)
    sortedBis = [i[0] for i in sortedList]
    
    sortedDict = collections.OrderedDict(zip(sortedBis, bigramFreq))
    
    #sortedBis = [i[0] for i in sortedDict]
    #sortedBis = sortedBis
    
    #found = set()
    #decrAlphabet: str = ''
    #for c in sortedBis:
     #   if not (c in found):
      #      found.add(c)
       #     decrAlphabet = decrAlphabet + c
        
   #decrAlphabet += decrAlphabet.upper() + symStr
    print(sortedDict)
    
    fChars = charFreq + symStr
    
    result: str = ''
    l = len(text)
    i = 1
    
    while (i < l - 1):
        p = text[i-1]
        c = text[i]
        n = text[i+1]
         
        bigram1 = (p + c).lower()
        bigram2 = (c + n).lower()
        
        v1 = charDict.get(bigram1)
        v2 = charDict.get(bigram2)
        r: str = ''
        index: int
        
        if v1:
            if v2:
                if max(v1, v2) == v1:
                    a = sortedDict.get(bigram1)
                    if a != None:
                        r = a[1]
                else:
                    a = sortedDict.get(bigram2)
                    if a != None:
                        r = a[0]
                    #r = sortedDict.get(bigram2)[0]
            else:
                a = sortedDict.get(bigram1)
                if a != None:
                    r = a[1]
                #r = sortedDict.get(bigram1)[1]
        else:
            if v2:
                a = sortedDict.get(bigram2)
                if a != None:
                    r = a[0]
             
        if r == '':
            r = c.translate(trns)
        result += r
        
        i = i + 1
    
    #print(fChars)
    
    #trns = str.maketrans(sortedChars, fChars)
    #result = text.translate(trns)
    #print(result)
    
    
    return(result)
    
enc = ''    
offset = int(input("Please enter your offset: ")) % 26

with open('thehobbit.txt', 'r', encoding='utf-8') as content_file:
    content = content_file.read()
    third = content[0:round(len(content) / 3)]
    freq = gatherFreq(third)
    enc = encryptCaesar(offset, content)
    
print("FREQ")
print(freq)
print()
    
with open('enc.txt', 'w', encoding='utf-8') as content_file:
    content_file.write(enc)

#decryptCaesar(offset, enc)
dec = decryptFrequency(enc, freq)

with open('decrypted.txt', 'w', encoding='utf-8') as content_file:
    content_file.write(dec)
    
dec2 = decryptBigramFrequency(enc, freq)
with open('decryptedBigram.txt', 'w', encoding='utf-8') as content_file:
    content_file.write(dec2)
