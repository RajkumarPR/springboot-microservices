#$PSVersionTable
#Get-help
function InvokeAndCheckStdOut($cmd, $successString, $failString) {
    Write-Host "====> InvokeAndCheckStdOut"
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

    if (! $success) {
      PlayWav "${env:windir}\Media\ding.wav"
      throw "Mvn command failed."
    }

    Write-Host "InvokeAndCheckStdOut <===="
}

function InvokeMvn($cmd) {
    InvokeAndCheckStdOut $cmd "BUILD SUCCESSFUL" "BUILD FAILED"
}


InvokeMvn "mvn clean install"