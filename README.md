# iOSScripts

## Generate Project script
```
tell application "Terminal"
	try
		do shell script "/usr/local/bin/xcodegen generate --spec /Users/user/Documents/lk-mobile/ioslk/Project.yml"
	on error errMsg number errNum
		display dialog "Путь к yaml файлу не найден в Документах. Перенесите файл Project.yml в строку ниже:" default answer ""
		set projectPath to text returned of result
		do script "/usr/local/bin/xcodegen generate --spec " & projectPath
	end try
end tell
```

## CocoaPods and XcodeGen install
```
tell application "Terminal"
	do script "brew install cocoapods"
	delay 5
	do shell script "/usr/local/bin/brew list | grep cocoapods"
	if result contains "cocoapods" then
		do script "/usr/local/bin/brew install xcodegen"
	end if
end tell
```

## Brew installer
```
tell application "Terminal"
	do script "brew install wget"
end tell
```
