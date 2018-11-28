  filename='direct.aac';
[y,Fs]=audioread(filename);
Fs = 48000; 
t = linspace(0,1,Fs); 
[b,d]=bandpass(y,[16000 22000],Fs)
figure(1)
title('Direct Path')
plot(b)

filename1='recorder1.aac';
[y,Fs]=audioread(filename1);
Fs = 48000; 
t = linspace(0,1,Fs); 
[a,d]=bandpass(y,[16000 22000],Fs)
figure(2)
title('Recorded Echoes')
plot(a)


r = xcorr(b,a)
figure(3)
title('Cross Correlation');
plot(r)


figure(4)
s = spectrogram(r);

spectrogram(r,'yaxis')

