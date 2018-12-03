tm= 1e-4
bw=1e5
fs=48000

waveform = phased.FMCWWaveform('SweepBandwidth',100.0e3,...
    'OutputFormat','Sweeps','NumSweeps',2);
plot(waveform)


sig = waveform();
subplot(211); plot(0:1/fs:tm-1/fs,real(sig));
xlabel('Time (s)'); ylabel('Amplitude (v)');
title('FMCW signal'); axis tight;
subplot(212); spectrogram(sig,32,16,32,fs,'yaxis');
title('FMCW signal spectrogram');