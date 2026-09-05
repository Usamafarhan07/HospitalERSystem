# commit.ps1 - commits progress in meaningful, component-grouped commits.
# Run this from inside the HospitalERSystem project folder (PowerShell).
# Safe to run repeatedly: it skips any group with nothing staged.

if (-not (Test-Path ".git")) {
    Write-Host "No git repo found. Running git init..."
    git init
}

function Commit-Group {
    param(
        [string]$Message,
        [string[]]$Files
    )

    $existing = $Files | Where-Object { Test-Path $_ }

    if (-not $existing) {
        Write-Host "Skipping '$Message' - no matching files found."
        return
    }

    git add $existing

    $staged = git diff --cached --name-only
    if (-not $staged) {
        Write-Host "Skipping '$Message' - nothing changed to commit."
    } else {
        git commit -m "$Message"
    }
}

Commit-Group -Message "Created project structure" -Files @("README.md", "commit.sh", "commit.ps1")
Commit-Group -Message "Implemented patient BST" -Files @("Patient.java", "PatientBST.java")
Commit-Group -Message "Implemented emergency queue" -Files @("EmergencyQueue.java")
Commit-Group -Message "Implemented treatment stack" -Files @("TreatmentRecord.java", "TreatmentStack.java")
Commit-Group -Message "Implemented patient visit history" -Files @("Visit.java", "VisitLinkedList.java")
Commit-Group -Message "Added main program and demo driver" -Files @("Main.java", "Demo.java")
Commit-Group -Message "Updated README" -Files @("README.md")

$hasOrigin = git remote get-url origin 2>$null
if ($hasOrigin) {
    $upstream = git rev-parse --abbrev-ref --symbolic-full-name '@{u}' 2>$null
    if ($upstream) {
        git push
    } else {
        git push -u origin main
    }
} else {
    Write-Host "No 'origin' remote set. Add one with:"
    Write-Host "  git remote add origin <your-repo-url>"
    Write-Host "then re-run this script."
}

Write-Host "Done."