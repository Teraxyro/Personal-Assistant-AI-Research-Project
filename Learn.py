from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import os
from selenium.common.exceptions import NoSuchElementException
import time
import pyodbc
import time
from pyodbc import ProgrammingError
from pyodbc import IntegrityError
from selenium.webdriver.chrome.options import Options

option = webdriver.ChromeOptions()
option.add_argument('headless')
PATH = "C:" + os.sep + "**LocationOfChromeWebdriver**"
print("Connecting to server...")
connection = pyodbc.connect('**SQLURL**')
cursor = connection.cursor()
print("Server connection successful!")



testElement = ''
count = 2
while True:
    blahh = 'C:' + os.sep + '**Location of words.txt**'
    File = open(r'**Location of words.txt**', 'r')
    f = File.readlines()
    newList = []
    for line in f:
        if line[-1] == '\n':
            newList.append(line[:-1])
        else:
            newList.append(line)
    file = open(r'**Location of words.txt**', "r+")
    file.truncate(0)
    file.close()
    if len(newList) > 0:
        READY = False
        for w in newList:
            word = w
            driver = webdriver.Chrome(PATH, options=option)
            driver.get('https://www.dictionary.com/browse/' + word + '?s=t')
            try:
                while 0==0:
                    testElement = driver.find_element_by_xpath('//*[@id="base-pw"]/main/section/section/div[1]/section['+ str(count) +']/h3').text
                    count = count + 1
                    g = ''
                    for c in testElement:
                        if c==' ' or c==',':
                            break
                        g = g + c
                    cnt = 0
                    gg = ''
                    for fg in g:
                        if cnt==0:
                            gg = gg + fg.upper()
                        if cnt == len(g) - 1:
                            gg = gg + fg
                            gg = gg + 's'
                        if cnt > 0 and cnt < len(g) - 1:
                            gg = gg + fg
                        cnt = cnt + 1
                    characterNo = []
                    ct = -1
                    Wrd = word.upper()
                    for wd in Wrd:
                        ct = ct + 1
                        if ct == 0:
                            if wd == 'A':
                                characterNo.append(str(1))
                            elif wd == 'B':
                                characterNo.append(str(2))
                            elif wd == 'C':
                                characterNo.append(str(3))
                            elif wd == 'D':
                                characterNo.append(str(4))
                            elif wd == 'E':
                                characterNo.append(str(5))
                            elif wd == 'F':
                                characterNo.append(str(6))
                            elif wd == 'G':
                                characterNo.append(str(7))
                            elif wd == 'H':
                                characterNo.append(str(8))
                            elif wd == 'I':
                                characterNo.append(str(9))
                            elif wd == 'J':
                                characterNo.append(str(10))
                            elif wd == 'K':
                                characterNo.append(str(11))
                            elif wd == 'L':
                                characterNo.append(str(12))
                            elif wd == 'M':
                                characterNo.append(str(13))
                            elif wd == 'N':
                                characterNo.append(str(14))
                            elif wd == 'O':
                                characterNo.append(str(15))
                            elif wd == 'P':
                                characterNo.append(str(16))
                            elif wd == 'Q':
                                characterNo.append(str(17))
                            elif wd == 'R':
                                characterNo.append(str(18))
                            elif wd == 'S':
                                characterNo.append(str(19))
                            elif wd == 'T':
                                characterNo.append(str(20))
                            elif wd == 'U':
                                characterNo.append(str(21))
                            elif wd == 'V':
                                characterNo.append(str(22))
                            elif wd == 'W':
                                characterNo.append(str(23))
                            elif wd == 'X':
                                characterNo.append(str(24))
                            elif wd == 'Y':
                                characterNo.append(str(25))
                            elif wd == 'Z':
                                characterNo.append(str(26))
                        else:
                            if wd == 'A':
                                characterNo.append(str(-1))
                            elif wd == 'B':
                                characterNo.append(str(-2))
                            elif wd == 'C':
                                characterNo.append(str(-3))
                            elif wd == 'D':
                                characterNo.append(str(-4))
                            elif wd == 'E':
                                characterNo.append(str(-5))
                            elif wd == 'F':
                                characterNo.append(str(-6))
                            elif wd == 'G':
                                characterNo.append(str(-7))
                            elif wd == 'H':
                                characterNo.append(str(-8))
                            elif wd == 'I':
                                characterNo.append(str(-9))
                            elif wd == 'J':
                                characterNo.append(str(-10))
                            elif wd == 'K':
                                characterNo.append(str(-11))
                            elif wd == 'L':
                                characterNo.append(str(-12))
                            elif wd == 'M':
                                characterNo.append(str(-13))
                            elif wd == 'N':
                                characterNo.append(str(-14))
                            elif wd == 'O':
                                characterNo.append(str(-15))
                            elif wd == 'P':
                                characterNo.append(str(-16))
                            elif wd == 'Q':
                                characterNo.append(str(-17))
                            elif wd == 'R':
                                characterNo.append(str(-18))
                            elif wd == 'S':
                                characterNo.append(str(-19))
                            elif wd == 'T':
                                characterNo.append(str(-20))
                            elif wd == 'U':
                                characterNo.append(str(-21))
                            elif wd == 'V':
                                characterNo.append(str(-22))
                            elif wd == 'W':
                                characterNo.append(str(-23))
                            elif wd == 'X':
                                characterNo.append(str(-24))
                            elif wd == 'Y':
                                characterNo.append(str(-25))
                            elif wd == 'Z':
                                characterNo.append(str(-26))
                    WORDID = ''
                    for chrctN in characterNo:
                        WORDID = WORDID + chrctN
                    print(word + ' processed')
                    x = 'INSERT INPUT'
                    count = count + 1
                    try:
                        try:
                            cursor.execute("INSERT INTO " + gg + " VALUES ('" + WORDID + "', '" + word.upper() + "', '" + x + "');")
                            cursor.commit()
                        except ProgrammingError:
                            cursor.execute("CREATE TABLE " + gg + " (WordID varchar(50), Word varchar(50), FunctionID varchar(50), PRIMARY KEY(WordID));")
                            connection.commit()
                            cursor.execute("INSERT INTO " + gg + " VALUES ('" + WORDID + "', '" + word.upper() + "', '" + x + "');")
                            connection.commit()
                            cursor.execute("ALTER TABLE GrammarFreq ADD " + gg + " float;")
                            connection.commit()


                    except IntegrityError:
                        print('repition detected')


            except NoSuchElementException:
                if count == 2:
                    try:
                        while 0==0:
                            testElement = driver.find_element_by_xpath('//*[@id="base-pw"]/main/section/section/div[1]/div/div[1]/section['+str(count)+']/h3/span[1]').text
                            count = count + 1
                            g = ''
                            for c in testElement:
                                if c == ' ' or c == ',':
                                    break
                                g = g + c
                            cnt = 0
                            gg = ''
                            for fg in g:
                                if cnt == 0:
                                    gg = gg + fg.upper()
                                if cnt == len(g) - 1:
                                    gg = gg + fg
                                    gg = gg + 's'
                                if cnt > 0 and cnt < len(g) - 1:
                                    gg = gg + fg
                                cnt = cnt + 1
                            characterNo = []
                            ct = -1
                            Wrd = word.upper()
                            for wd in Wrd:
                                ct = ct + 1
                                if ct == 0:
                                    if wd == 'A':
                                        characterNo.append(str(1))
                                    elif wd == 'B':
                                        characterNo.append(str(2))
                                    elif wd == 'C':
                                        characterNo.append(str(3))
                                    elif wd == 'D':
                                        characterNo.append(str(4))
                                    elif wd == 'E':
                                        characterNo.append(str(5))
                                    elif wd == 'F':
                                        characterNo.append(str(6))
                                    elif wd == 'G':
                                        characterNo.append(str(7))
                                    elif wd == 'H':
                                        characterNo.append(str(8))
                                    elif wd == 'I':
                                        characterNo.append(str(9))
                                    elif wd == 'J':
                                        characterNo.append(str(10))
                                    elif wd == 'K':
                                        characterNo.append(str(11))
                                    elif wd == 'L':
                                        characterNo.append(str(12))
                                    elif wd == 'M':
                                        characterNo.append(str(13))
                                    elif wd == 'N':
                                        characterNo.append(str(14))
                                    elif wd == 'O':
                                        characterNo.append(str(15))
                                    elif wd == 'P':
                                        characterNo.append(str(16))
                                    elif wd == 'Q':
                                        characterNo.append(str(17))
                                    elif wd == 'R':
                                        characterNo.append(str(18))
                                    elif wd == 'S':
                                        characterNo.append(str(19))
                                    elif wd == 'T':
                                        characterNo.append(str(20))
                                    elif wd == 'U':
                                        characterNo.append(str(21))
                                    elif wd == 'V':
                                        characterNo.append(str(22))
                                    elif wd == 'W':
                                        characterNo.append(str(23))
                                    elif wd == 'X':
                                        characterNo.append(str(24))
                                    elif wd == 'Y':
                                        characterNo.append(str(25))
                                    elif wd == 'Z':
                                        characterNo.append(str(26))
                                else:
                                    if wd == 'A':
                                        characterNo.append(str(-1))
                                    elif wd == 'B':
                                        characterNo.append(str(-2))
                                    elif wd == 'C':
                                        characterNo.append(str(-3))
                                    elif wd == 'D':
                                        characterNo.append(str(-4))
                                    elif wd == 'E':
                                        characterNo.append(str(-5))
                                    elif wd == 'F':
                                        characterNo.append(str(-6))
                                    elif wd == 'G':
                                        characterNo.append(str(-7))
                                    elif wd == 'H':
                                        characterNo.append(str(-8))
                                    elif wd == 'I':
                                        characterNo.append(str(-9))
                                    elif wd == 'J':
                                        characterNo.append(str(-10))
                                    elif wd == 'K':
                                        characterNo.append(str(-11))
                                    elif wd == 'L':
                                        characterNo.append(str(-12))
                                    elif wd == 'M':
                                        characterNo.append(str(-13))
                                    elif wd == 'N':
                                        characterNo.append(str(-14))
                                    elif wd == 'O':
                                        characterNo.append(str(-15))
                                    elif wd == 'P':
                                        characterNo.append(str(-16))
                                    elif wd == 'Q':
                                        characterNo.append(str(-17))
                                    elif wd == 'R':
                                        characterNo.append(str(-18))
                                    elif wd == 'S':
                                        characterNo.append(str(-19))
                                    elif wd == 'T':
                                        characterNo.append(str(-20))
                                    elif wd == 'U':
                                        characterNo.append(str(-21))
                                    elif wd == 'V':
                                        characterNo.append(str(-22))
                                    elif wd == 'W':
                                        characterNo.append(str(-23))
                                    elif wd == 'X':
                                        characterNo.append(str(-24))
                                    elif wd == 'Y':
                                        characterNo.append(str(-25))
                                    elif wd == 'Z':
                                        characterNo.append(str(-26))
                            WORDID = ''
                            for chrctN in characterNo:
                                WORDID = WORDID + chrctN
                            print(word + ' processed')
                            x = 'INSERT INPUT'
                            try:
                                try:
                                    cursor.execute(
                                        "INSERT INTO " + gg + " VALUES ('" + WORDID + "', '" + word.upper() + "', '" + x + "');")
                                    cursor.commit()
                                except ProgrammingError:
                                    cursor.execute(
                                        "CREATE TABLE " + gg + " (WordID varchar(50), Word varchar(50), FunctionID varchar(50), PRIMARY KEY(WordID));")
                                    connection.commit()
                                    cursor.execute(
                                        "INSERT INTO " + gg + " VALUES ('" + WORDID + "', '" + word.upper() + "', '" + x + "');")
                                    connection.commit()
                                    cursor.execute("ALTER TABLE GrammarFreq ADD " + gg + " float;");
                                    connection.commit()
                            except IntegrityError:
                                print('repition detected')

                    except NoSuchElementException:
                        if count == 2:
                            try:
                                while 0 == 0:
                                    testElement = driver.find_element_by_xpath('//*[@id="base-pw"]/main/section/section/div[1]/div/div[1]/section[' + str(count) + ']/h3/span').text
                                    count = count + 1
                                    g = ''
                                    for c in testElement:
                                        if c == ' ' or c == ',':
                                            break
                                        g = g + c
                                    cnt = 0
                                    gg = ''
                                    for fg in g:
                                        if cnt == 0:
                                            gg = gg + fg.upper()
                                        if cnt == len(g) - 1:
                                            gg = gg + fg
                                            gg = gg + 's'
                                        if cnt > 0 and cnt < len(g) - 1:
                                            gg = gg + fg
                                        cnt = cnt + 1
                                    characterNo = []
                                    ct = -1
                                    Wrd = word.upper()
                                    for wd in Wrd:
                                        ct = ct + 1
                                        if ct == 0:
                                            if wd == 'A':
                                                characterNo.append(str(1))
                                            elif wd == 'B':
                                                characterNo.append(str(2))
                                            elif wd == 'C':
                                                characterNo.append(str(3))
                                            elif wd == 'D':
                                                characterNo.append(str(4))
                                            elif wd == 'E':
                                                characterNo.append(str(5))
                                            elif wd == 'F':
                                                characterNo.append(str(6))
                                            elif wd == 'G':
                                                characterNo.append(str(7))
                                            elif wd == 'H':
                                                characterNo.append(str(8))
                                            elif wd == 'I':
                                                characterNo.append(str(9))
                                            elif wd == 'J':
                                                characterNo.append(str(10))
                                            elif wd == 'K':
                                                characterNo.append(str(11))
                                            elif wd == 'L':
                                                characterNo.append(str(12))
                                            elif wd == 'M':
                                                characterNo.append(str(13))
                                            elif wd == 'N':
                                                characterNo.append(str(14))
                                            elif wd == 'O':
                                                characterNo.append(str(15))
                                            elif wd == 'P':
                                                characterNo.append(str(16))
                                            elif wd == 'Q':
                                                characterNo.append(str(17))
                                            elif wd == 'R':
                                                characterNo.append(str(18))
                                            elif wd == 'S':
                                                characterNo.append(str(19))
                                            elif wd == 'T':
                                                characterNo.append(str(20))
                                            elif wd == 'U':
                                                characterNo.append(str(21))
                                            elif wd == 'V':
                                                characterNo.append(str(22))
                                            elif wd == 'W':
                                                characterNo.append(str(23))
                                            elif wd == 'X':
                                                characterNo.append(str(24))
                                            elif wd == 'Y':
                                                characterNo.append(str(25))
                                            elif wd == 'Z':
                                                characterNo.append(str(26))
                                        else:
                                            if wd == 'A':
                                                characterNo.append(str(-1))
                                            elif wd == 'B':
                                                characterNo.append(str(-2))
                                            elif wd == 'C':
                                                characterNo.append(str(-3))
                                            elif wd == 'D':
                                                characterNo.append(str(-4))
                                            elif wd == 'E':
                                                characterNo.append(str(-5))
                                            elif wd == 'F':
                                                characterNo.append(str(-6))
                                            elif wd == 'G':
                                                characterNo.append(str(-7))
                                            elif wd == 'H':
                                                characterNo.append(str(-8))
                                            elif wd == 'I':
                                                characterNo.append(str(-9))
                                            elif wd == 'J':
                                                characterNo.append(str(-10))
                                            elif wd == 'K':
                                                characterNo.append(str(-11))
                                            elif wd == 'L':
                                                characterNo.append(str(-12))
                                            elif wd == 'M':
                                                characterNo.append(str(-13))
                                            elif wd == 'N':
                                                characterNo.append(str(-14))
                                            elif wd == 'O':
                                                characterNo.append(str(-15))
                                            elif wd == 'P':
                                                characterNo.append(str(-16))
                                            elif wd == 'Q':
                                                characterNo.append(str(-17))
                                            elif wd == 'R':
                                                characterNo.append(str(-18))
                                            elif wd == 'S':
                                                characterNo.append(str(-19))
                                            elif wd == 'T':
                                                characterNo.append(str(-20))
                                            elif wd == 'U':
                                                characterNo.append(str(-21))
                                            elif wd == 'V':
                                                characterNo.append(str(-22))
                                            elif wd == 'W':
                                                characterNo.append(str(-23))
                                            elif wd == 'X':
                                                characterNo.append(str(-24))
                                            elif wd == 'Y':
                                                characterNo.append(str(-25))
                                            elif wd == 'Z':
                                                characterNo.append(str(-26))
                                    WORDID = ''
                                    for chrctN in characterNo:
                                        WORDID = WORDID + chrctN
                                    print(word + ' processed')
                                    x = 'INSERT INPUT'
                                    try:
                                        try:
                                            cursor.execute(
                                                "INSERT INTO " + gg + " VALUES ('" + WORDID + "', '" + word.upper() + "', '" + x + "');")
                                            cursor.commit()
                                        except ProgrammingError:
                                            cursor.execute(
                                                "CREATE TABLE " + gg + " (WordID varchar(50), Word varchar(50), FunctionID varchar(50), PRIMARY KEY(WordID));")
                                            connection.commit()
                                            cursor.execute(
                                                "INSERT INTO " + gg + " VALUES ('" + WORDID + "', '" + word.upper() + "', '" + x + "');")
                                            connection.commit()
                                            cursor.execute("ALTER TABLE GrammarFreq ADD " + gg + " float;");
                                            connection.commit()
                                    except IntegrityError:
                                        print("repition detected")

                            except NoSuchElementException:
                                print('error!')
                        else:
                            count = 2
                else:
                    count = 2
            driver.close()
        File.close()
        time.sleep(2)
        break


