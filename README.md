# Matlab-Code-Formatter

Matlab does not have a good code formatting function like IDEA and other integrated development environments. We can only use CTRL + I to indent the code, but it can't add some space to maintain the spacing between codes. Although most developers have the habit of adding spacing, but not everyone is. Code without spacing is poor readability. The amount of effort to add spacing in code with too many lines is a lot of work and tedious.

## Introduction

```matlab
for i=1:length(nums)
    x=x+x*x/x;y=1;%demo
    s='a/b-c*';%ignore string
    s='I''m';
end
```

```matlab
for i = 1 : length(nums)
    x = x + x * x / x; y = 1; % demo
    s = 'a/b-c*'; % ignore string
    s = 'I''m';
end
```

## Configuration

- Add the fmt folder to the search path of Matlab.

- Modify the path of Formatter.jar in fmt.m.

## Useage

Copy the code, then execute fmt in the Matlab command line window. After that, the formatted code whill replaces the original content in the clipboard, so paste it back. Finish!

> ctrl+c -> fmt -> ctrl+v

## Attention

If you modify the Formatter.java content and repack it, you need to make sure that the JDK version used for compilation is less than the JDK version of Matlab. The JDK version of Matlab is generally 1.7,  you can use below command to check it.

```matlab
version -java
```

