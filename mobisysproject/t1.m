filename1='recorder.aac';
[y,Fs]=audioread(filename1);
Fs = 48000; 
t = linspace(0,1,Fs); 
[a,d]=bandpass(y,[20000 22000],Fs)
 figure(1)
% subplot(2,1,1)
 plot(a)
  
 
 bandpass(y,[20000 22000],Fs)