if exist('formatter') == 0
	javaaddpath('D:\\fmt\\Formatter.jar');
	import mypackage.*;
	formatter = Formatter();
end

formatter.format();
display('Code has been formatted!');
