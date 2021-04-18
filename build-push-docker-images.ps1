function InvokeAndCheckStdOut($cmd, $successString, $failString) {
    Write-Host "====> InvokeAndCheckStdIn"
    $fullCmd = "$cmd|Tee-Object -variable result" 

    Invoke-Expression $fullCmd
    $found = $false
    $success = $false
    foreach ($line in $result) {
      if ($line -match $failString) {
       $found = $true
       $success = $false
       break
      }
      else {
       if ($line -match $successString) {
        $found = $true
        $success = $true
        #"[InvokeAndCheckStdOut] FOUND MATCH: $line"
        break
       }
       else {
        #"[InvokeAndCheckStdOut] $line"
       }
      }
    }

    Write-Host "InvokeAndCheckStdOut <===="
}

function InvokeMvn($cmd) {
    InvokeAndCheckStdOut $cmd "BUILD SUCCESSFUL" "BUILD FAILED"
}

$dirList = "cloud-config-server", "cloud-gateway", "department-service", "user-service", "department-service","hystrix-dashboard"

foreach ($dir in $dirList) {
	cd $dir
	write-host("Currently building and pushing : "+$dir)
	InvokeMvn "mvn clean package dockerfile:push"
	cd ../

}