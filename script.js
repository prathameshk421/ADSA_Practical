document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('codes-container');
    const filterBtns = document.querySelectorAll('.filter-btn');

    // Function to render codes
    function renderCodes(filter) {
        container.innerHTML = '';
        
        const filteredData = filter === 'all' 
            ? codesData 
            : codesData.filter(item => item.assignment === filter);

        if(filteredData.length === 0) {
            container.innerHTML = '<p style="text-align:center; color: var(--text-secondary);">No practice codes found for this assignment.</p>';
            return;
        }

        filteredData.forEach((item, index) => {
            const card = document.createElement('div');
            card.className = 'code-card';
            card.style.animationDelay = `${index * 0.1}s`;

            const cleanCode = item.code.replace(/</g, "&lt;").replace(/>/g, "&gt;");

            card.innerHTML = `
                <div class="card-header">
                    <h2 class="card-title">${item.title}</h2>
                    <div class="complexity-badge">
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" style="margin-right: 6px;"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>
                        ${item.complexity}
                    </div>
                </div>
                <div class="code-container">
                    <div class="code-header">
                        <span class="language-label">Java</span>
                        <button class="copy-btn" onclick="copyCode(this)">Copy Code</button>
                    </div>
                    <pre><code class="language-java">${cleanCode}</code></pre>
                </div>
            `;
            container.appendChild(card);
        });

        // Re-highlight syntax
        document.querySelectorAll('pre code').forEach((el) => {
            hljs.highlightElement(el);
        });
    }

    // Initial render
    renderCodes('all');

    // Filter functionality
    filterBtns.forEach(btn => {
        btn.addEventListener('click', (e) => {
            // Update active state
            filterBtns.forEach(b => b.classList.remove('active'));
            e.target.classList.add('active');

            // Filter codes
            const filter = e.target.getAttribute('data-filter');
            renderCodes(filter);
        });
    });
});

// Copy to clipboard functionality
window.copyCode = function(button) {
    const codeBlock = button.parentElement.nextElementSibling.querySelector('code');
    const textArea = document.createElement('textarea');
    textArea.value = codeBlock.innerText;
    document.body.appendChild(textArea);
    textArea.select();
    
    try {
        document.execCommand('copy');
        const originalText = button.innerText;
        button.innerText = 'Copied!';
        button.style.color = '#34d399';
        button.style.borderColor = '#34d399';
        
        setTimeout(() => {
            button.innerText = originalText;
            button.style.color = '';
            button.style.borderColor = '';
        }, 2000);
    } catch (err) {
        console.error('Failed to copy text', err);
    }
    
    document.body.removeChild(textArea);
};
