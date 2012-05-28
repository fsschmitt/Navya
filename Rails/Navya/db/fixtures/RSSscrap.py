import urllib2
import os
from bs4 import BeautifulSoup
import time


#DESPORTO

val = 1
newpath = r'Noticias2/Desporto/'
newpath_string = 'Noticias2/Desporto/'
if not os.path.exists(newpath): os.makedirs(newpath)
soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoDesporto?format=xml').read())

def getNews() :
	global soup
	for item in soup.findAll('item'):
		global val, newpath, newpath_string
		f = open(newpath_string+'%i.txt' % (val+time.time()),'w+')
		desc = item.description.string
		num = desc.find("<")
		f.write(item.title.string.encode('UTF-8')+'\n')
		f.write('[urlppro]'+ item.link.string.encode('UTF-8')+ '[/urlppro]' + '\n')
		f.write(desc[0:num].encode('UTF-8'))
		val = val + 1
	return

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Desporto').read())

getNews()


#CULTURA E BOA VIDA

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoCultura?format=xml').read())
val = 1;
newpath = r'Noticias2/CulturaeLazer/'
if not os.path.exists(newpath): os.makedirs(newpath)
newpath_string = 'Noticias2/CulturaeLazer/'

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Cultura').read())

getNews()

#POLITICA E ECONOMIA

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoPolitica?format=xml').read())

newpath = r'Noticias2/EconomiaePolitica/'
if not os.path.exists(newpath): os.makedirs(newpath)
newpath_string = 'Noticias2/EconomiaePolitica/'

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Politica').read())

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Economia').read())

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://economico.sapo.pt/rss/politica.html').read())

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://economico.sapo.pt/rss/economia.html').read())

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://economico.sapo.pt/rss/mercados.html').read())

getNews()



#TECNOLOGIA E CIENCIA
val = 1

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoTecnologia').read())

newpath = r'Noticias2/CienciaeTecnologia/'
if not os.path.exists(newpath): os.makedirs(newpath)
newpath_string = 'Noticias2/CienciaeTecnologia/'

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Tecnologia').read())

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoCiencias').read())

getNews()


#SOCIEDADE
val = 1

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Sociedade').read())

newpath = r'Noticias2/Sociedade/'
if not os.path.exists(newpath): os.makedirs(newpath)
newpath_string = 'Noticias2/Sociedade/'

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoSociedade').read())

getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoEducacao').read())

getNews()