       Fs=48000;                   % Sampling Frequency (Hz)
%Tmax = 0.01;                               % Duration (sec)
%t = linspace(0, Tmax, Tmax*Fs);
m4AFilename = 'recorder1.aac';
 x = audioread(m4AFilename);
  wavFilename = 'reco23.wav';
  audiowrite(wavFilename,x,Fs);
    
%    figure(1)
%    [y,Fs]=audioread('reco23.wav');
%    t=linspace(0,length(y)/Fs,length(y));
%    plot(t,y)
%    
%        y = y(:,1);
%     dt = 1/Fs;
%     t = 0:dt:(length(y)*dt)-dt;
%     plot(t,y); xlabel('Seconds'); ylabel('Amplitude');
%     figure
%     plot(periodogram(periodogram,y,'Fs',Fs,'NFFT',length(y)));
    
    
%     filename='recorder1.aac';
% [y,Fs]=audioread(filename);
% z=highpass(y,Fs/4,Fs)
% x = fft2(z);
% imagesc(abs(x));
    
    filename='direct2.aac';
[y,Fs]=audioread(filename);
Fs = 48000; 
t = linspace(0,1,Fs); 
[b,d]=bandpass(y,[16000 22000],Fs)
figure(2)
title('Direct Path')
plot(b)


%%r = xcorr(x,y)

   filename1='recorder1.aac';
[y,Fs]=audioread(filename1);
Fs = 48000; 
t = linspace(0,1,Fs); 
[a,d]=bandpass(y,[16000 22000],Fs)
figure(1)
subplot(2,1,1)
plot(a)

title('Recorded Echoes')

r = xcorr(b,a)
title('Cross Correlation');
subplot(2,1,2)
plot(r)

figure(3)
s = spectrogram(r);

spectrogram(r,'yaxis')


    
%    Nfft=1024;
%    f=linspace(0,Fs,Nfft);
%    G=abs(fft(y,Nfft));
%    plot(f(1:Nfft/2),G(1:Nfft/2))
%    
   